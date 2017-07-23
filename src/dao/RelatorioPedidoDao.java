package dao;

import connection.ConnectionFactory;
import exceptions.BancoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.VendaCliente;

public class RelatorioPedidoDao extends DaoPadrao{
    
    public ResultSet getAll(String id)throws BancoException{
        
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
                        
            String sql =    "select  c.ds_produto, "
                            +"b.qt_venda, "
                            +"round(case when tipo_pgto = 0 then "
                                 +"vl_venda else vl_prazo "
                            +"end,2) vl_unitario, "
                               +"round(b.qt_venda * (case when tipo_pgto = 0 then "
                                 +"vl_venda else vl_prazo "
                            +"end),2)vl_total "
            +"from venda a "
            +"join venda_item b on(a.id = b.id_venda) "
            +"join produto c on(b.id_produto = c.id) "
            +"where a.id = "+id.trim();
            
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            return rs;
            
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }
  
    
    public VendaCliente getDadosVenda(String idVenda)throws BancoException{
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();

            String sql = "select  a.id, b.ds_nome, "
            +"  b.nr_telefone, "
            +"  b.ds_email, "
            +"  c.ds_bairro, "
            +"  f.nr_cep, "
            +"  d.ds_municipio, "
            +"  e.ds_uf, "
            +"  b.ds_endereco "
            +"from venda a "
            +"join pessoa b on(a.id_cliente = b.id) "
            +"left join bairro c on(b.id_bairro = c.id) "
            +"left join municipio d on(b.id_municipio = d.id) "
            +"left join uf e on(b.id_uf = e.id) "
            +"left join cep f on(b.id_cep = f.id) "
            +"where a.id = ?";
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, idVenda);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                
                VendaCliente venda = new VendaCliente();

                venda.setBairro(rs.getString("ds_bairro"));
                venda.setCep(rs.getString("nr_cep"));
                venda.setEmailCliente(rs.getString("ds_email"));
                venda.setFoneCliente(rs.getString("nr_telefone"));
                venda.setMunicipio(rs.getString("ds_municipio"));
                venda.setNomeCliente(rs.getString("ds_nome"));
                venda.setUf(rs.getString("ds_uf"));
                venda.setIdVenda(rs.getInt("id"));
                venda.setEndereco(rs.getString("ds_endereco"));
                
                return venda;
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }
    
    public String getValorVenda(String idVenda)throws BancoException{
        Connection conn = null;
        PreparedStatement pst = null;
        String valor="";
        
        try {
            conn = ConnectionFactory.getConnection();

            String sql =    "select round(sum(b.qt_venda * (case when tipo_pgto = 0 then "
                                 +"vl_venda else vl_prazo "
                            +"end)),2)vl_total "
            +"from venda a "
            +"join venda_item b on(a.id = b.id_venda) "
            +"join produto c on(b.id_produto = c.id) "
            +"where a.id = "+idVenda.trim();

            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                
                valor =  rs.getString("vl_total");
                return valor;
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return valor;
    }
    
}
