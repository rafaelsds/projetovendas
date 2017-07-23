package dao;

import connection.ConnectionFactory;
import exceptions.BancoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Uf;

public class UfDao extends DaoPadrao{
    public void insert(Uf uf) throws BancoException{
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = ConnectionFactory.getConnection();
            
            String sql = "insert into uf (ds_uf) values (?)";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, uf.getNome());
            
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
            String sql = "delete from uf where id = ?";
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
    
    public void update(int codigo, Uf uf) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update uf set ds_uf = ? where id = ?";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, uf.getNome());
            pst.setInt(2, codigo);

            pst.execute();
            conn.commit();
        } catch(SQLException e) {  
            erro(conn, "Erro ao atualizar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    public List<Uf> getAll() throws BancoException {
        List<Uf> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from uf";
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                Integer id = rs.getInt("id");
                String ds_uf = rs.getString("ds_uf");
                
                Uf uf = new Uf();

                uf.setId(id);
                uf.setNome(ds_uf);

                lista.add(uf);
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return lista;
    }
    
    public Uf getUf(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from uf where id = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                
                Integer id = rs.getInt("id");
                String ds_uf = rs.getString("ds_uf");
                
                Uf uf = new Uf();

                uf.setId(id);
                uf.setNome(ds_uf);
                
                return uf;
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }
}
