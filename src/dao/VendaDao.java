/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionFactory;
import exceptions.BancoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Venda;
import model.VendaItem;


public class VendaDao extends DaoPadrao{
    
    public List<Venda> getAll() throws BancoException {
        List<Venda> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select a.*,b.ds_nome from venda a join pessoa b on(b.id = a.id_cliente)";
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Venda v = new Venda();

                v.setId(rs.getInt("id"));
                v.setDescricao(rs.getString("ds_venda"));
                v.setIdCliente(rs.getInt("id_cliente"));
                v.setNomeCliente(rs.getString("ds_nome"));
                v.setIdVendedor(rs.getInt("id_vendedor"));
                v.setTipoPagamento(rs.getInt("tipo_pgto"));
                v.setValorAcrescimo(rs.getInt("vl_acrescimo"));
                v.setValorDesconto(rs.getInt("vl_desconto"));
                lista.add(v);
            }
        } catch(SQLException e) {
            erro(conn, "Erro ao buscar registro", e);
        } finally {
            finaliza(conn, pst);
        }
        return lista;
    }
    
    
    public Venda getVenda(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select a.*,b.ds_nome from venda a join pessoa b on(b.id = a.id_cliente) where a.id = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                Venda v = new Venda();

                v.setId(rs.getInt("id"));
                v.setDescricao(rs.getString("ds_venda"));
                v.setIdCliente(rs.getInt("id_cliente"));
                v.setNomeCliente(rs.getString("ds_nome"));
                v.setIdVendedor(rs.getInt("id_vendedor"));
                v.setTipoPagamento(rs.getInt("tipo_pgto"));
                v.setValorAcrescimo(rs.getInt("vl_acrescimo"));
                v.setValorDesconto(rs.getInt("vl_desconto"));

                return v;
            }
        } catch(SQLException e) {
            erro(conn, "Erro ao obter registro", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }

    
    public void update(int codigo, Venda v) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update venda set id_cliente = ?, id_vendedor = ?, tipo_pgto = ?,vl_desconto = ?, vl_acrescimo = ?, ds_venda = ?, tipo_pgto=? "
                    + " where id = ?";
            pst = conn.prepareStatement(sql);

            pst.setInt(1, v.getIdCliente());
            pst.setInt(2, v.getIdVendedor());
            pst.setInt(3, v.getTipoPagamento());
            pst.setInt(4, v.getValorDesconto());
            pst.setInt(5, v.getValorAcrescimo());
            pst.setString(6, v.getDescricao());
            pst.setInt(7, v.getTipoPagamento());
            pst.setInt(8, codigo);
            
            pst.execute();
            conn.commit();
        } catch(SQLException e) {
            erro(conn, "Erro ao atualizar registro", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    public void delete(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            deleteProduto(codigo);
        }catch(BancoException e){
            e.printStackTrace();
        }
        
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "delete from venda where id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            pst.execute();

            conn.commit();
        } catch(SQLException e) {
            erro(conn, "Erro ao excluir registro", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    
    public void insert(Venda v)throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = ConnectionFactory.getConnection();

            String sql = "insert into venda (id_cliente, id_vendedor, tipo_pgto, vl_desconto, vl_acrescimo, ds_venda,tipo_pgto) values (?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, v.getIdCliente());
            pst.setInt(2, v.getIdVendedor());
            pst.setInt(3, v.getTipoPagamento());
            pst.setInt(4, v.getValorDesconto());
            pst.setInt(5, v.getValorAcrescimo());
            pst.setString(6, v.getDescricao());
            pst.setInt(7, v.getTipoPagamento());
            
            pst.execute();
            conn.commit();

        } catch(SQLException e) {
            erro(conn, "Erro ao inserir registro", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    public void insertProduto(List<VendaItem> vendaItem, Integer id)throws BancoException {
        Integer idVenda=0;
        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            if(id != null && !id.toString().isEmpty() && id >0){
                idVenda=id;
            }else{
                idVenda = getUltimoIdVenda();
            }
        }catch(BancoException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        try{
            deleteProduto(idVenda);
        }catch(BancoException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        try {
            conn = ConnectionFactory.getConnection();
            for(VendaItem v: vendaItem){
                String sql = "insert into venda_item (id_venda, id_produto, qt_venda) values (?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, idVenda);
                pst.setInt(2, v.getIdProduto());
                pst.setInt(3, v.getQtProduto());
                
                pst.execute();
                conn.commit();
            }
            
        } catch(SQLException e) {
            erro(conn, "Erro ao inserir registro", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    public Integer getUltimoIdVenda() throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        Integer id;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select max(id)id from venda";

            pst = conn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                id = rs.getInt("id");
                
                return id;
            }
        } catch(SQLException e) {
            erro(conn, "Erro ao obter registro", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }
    
    public void deleteProduto(int idVenda) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "delete from venda_item where id_venda = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idVenda);
            pst.execute();

            conn.commit();
        } catch(SQLException e) {
            erro(conn, "Erro ao excluir registro", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    public List<VendaItem> getProdutos(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        List<VendaItem> lista = new ArrayList<>();
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select a.*, c.ds_produto, nvl(case when tipo_pgto = 0 then " +
                        " vl_venda else vl_prazo end,0) vl_produto " +
                        " from venda_item a join venda b on(a.id_venda = b.id) " +
                        " join produto c on(a.id_produto = c.id) where id_venda = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {

                VendaItem v = new VendaItem();

                v.setIdProduto(rs.getInt("id_produto"));
                v.setIdVenda(rs.getInt("id_venda"));
                v.setQtProduto(rs.getInt("qt_venda"));
                v.setDescricaoProduto(rs.getString("ds_produto"));
                v.setValorProduto(rs.getInt("vl_produto"));
                v.setValorTotal(rs.getInt("qt_venda")*rs.getInt("vl_produto"));
                
                lista.add(v);
                
            }
        } catch(SQLException e) {
            erro(conn, "Erro ao obter registro", e);
        } finally {
            finaliza(conn, pst);
        }
        
        return lista;
    }
    
}
