package dao;

import connection.ConnectionFactory;
import exceptions.BancoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Municipio;

public class MunicipioDao extends DaoPadrao{
    public void insert(Municipio municipio)throws BancoException {
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = ConnectionFactory.getConnection();
            
            String sql = "insert into municipio (ds_municipio, cd_ibge) values (?, ?)";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, municipio.getNome());
            pst.setString(2, municipio.getIbge());
            
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
            String sql = "delete from municipio where id = ?";
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
    
    public void update(int codigo, Municipio municipio) throws BancoException, SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update municipio set ds_municipio = ?, cd_ibge = ? where id = ?";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, municipio.getNome());
            pst.setString(2, municipio.getIbge());
            pst.setInt(3, codigo);

            pst.execute();
            conn.commit();
        } catch(SQLException e) {  
            erro(conn, "Erro ao atualizar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    public List<Municipio> getAll() throws BancoException {
        List<Municipio> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from municipio";
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                Integer id = rs.getInt("id");
                String ds_municipio = rs.getString("ds_municipio");
                String cod_ibge = rs.getString("cd_ibge");

                Municipio municipio = new Municipio();

                municipio.setId(id);
                municipio.setNome(ds_municipio);
                municipio.setIbge(cod_ibge);

                lista.add(municipio);
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return lista;
    }
    
    public Municipio getMunicipio(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from municipio where id = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                
                Integer id = rs.getInt("id");
                String ds_municipio = rs.getString("ds_municipio");
                String cod_ibge = rs.getString("cd_ibge");
                
                Municipio municipio = new Municipio();

                municipio.setId(id);
                municipio.setNome(ds_municipio);
                municipio.setIbge(cod_ibge);
                

                return municipio;
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }
}
