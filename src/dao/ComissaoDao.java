package dao;

import connection.ConnectionFactory;
import exceptions.BancoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Comissao;

public class ComissaoDao extends DaoPadrao{    
    
    public void insert(Comissao comissao) throws BancoException{
        Connection con = null;
        PreparedStatement pst = null;
        Timestamp dataInicial=null, dtFinal=null;
        
        if(comissao.getDataInicio() != null){
            dataInicial = new Timestamp(comissao.getDataInicio().getTime());
        }
        
        if(comissao.getDataFinal()!= null){
            dtFinal = new Timestamp(comissao.getDataFinal().getTime());
        }

        try {
            con = ConnectionFactory.getConnection();
            
            String sql = "insert into comissao (vl_comissao,dt_inicio_vigencia,dt_final_vigencia) values (?,?,?)";
            
            pst = con.prepareStatement(sql);
            pst.setInt(1, comissao.getValor());
            pst.setTimestamp(2, dataInicial);
            pst.setTimestamp(3, dtFinal);
            pst.execute();
            con.commit();
            
        } catch(SQLException e) {  
            erro(con, "Erro ao inserir registro ", e);
        } finally {
            finaliza(con, pst);
        }
    }
    
    public void delete(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "delete from comissao where id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            pst.execute();

            conn.commit();
        } catch(SQLException e) {  
            erro(conn, "Erro ao excluir registro ", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    public void update(int codigo, Comissao comissao) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        Timestamp dataInicial=null, dtFinal=null;
        
        if(comissao.getDataInicio() != null){
            dataInicial = new Timestamp(comissao.getDataInicio().getTime());
        }
        
        if(comissao.getDataFinal()!= null){
            dtFinal = new Timestamp(comissao.getDataFinal().getTime());
        }
        
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update comissao set vl_comissao = ?,dt_inicio_vigencia = ?,dt_final_vigencia = ? where id = ?";
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, comissao.getValor());
            pst.setTimestamp(2, dataInicial);
            pst.setTimestamp(3, dtFinal);
            pst.setInt(4, codigo);

            pst.execute();
            conn.commit();
        } catch(SQLException e) {  
            erro(conn, "Erro ao atualizar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    public List<Comissao> getAll() throws BancoException {
        List<Comissao> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from comissao";
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                Integer id = rs.getInt("id");
                Integer valor = rs.getInt("vl_comissao");
                java.sql.Date dataInicio = rs.getDate("dt_inicio_vigencia");
                java.sql.Date dataFinal = rs.getDate("dt_final_vigencia");

                Comissao comissao = new Comissao();

                comissao.setId(id);
                comissao.setValor(valor);
                comissao.setDataInicio(dataInicio);
                comissao.setDataFinal(dataFinal);

                lista.add(comissao);
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return lista;
    }
    
    public Comissao getComissao(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from comissao where id = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                
                Integer id = rs.getInt("id");
                Integer valor = rs.getInt("vl_comissao");
                java.sql.Date dataInicio = rs.getDate("dt_inicio_vigencia");
                java.sql.Date dataFinal = rs.getDate("dt_final_vigencia");


                Comissao comissao = new Comissao();

                comissao.setId(id);
                comissao.setValor(valor);
                comissao.setDataInicio(dataInicio);
                comissao.setDataFinal(dataFinal);     
                

                return comissao;
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }
    
}


