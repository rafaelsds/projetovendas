package dao;

import connection.ConnectionFactory;
import exceptions.BancoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Transportador;

public class TransportadorDao extends DaoPadrao{
     public void insert(Transportador transportador)throws BancoException {
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = ConnectionFactory.getConnection();
            
            String sql = "insert into transportador (ds_transportador, ds_placa) values (?, ?)";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, transportador.getNome());
            pst.setString(2, transportador.getPlaca());
            
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
            String sql = "delete from transportador where id = ?";
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
    
    public void update(int codigo, Transportador transportador) throws BancoException, SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update transportador set ds_transportador = ?, ds_placa = ? where id = ?";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, transportador.getNome());
            pst.setString(2, transportador.getPlaca());
            pst.setInt(3, codigo);

            pst.execute();
            conn.commit();
        } catch(SQLException e) {  
            erro(conn, "Erro ao atualizar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    public List<Transportador> getAll() throws BancoException {
        List<Transportador> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from transportador";
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                Integer id = rs.getInt("id");
                String ds_nome = rs.getString("ds_transportador");
                String ds_placa = rs.getString("ds_placa");

                Transportador transportador = new Transportador();

                transportador.setId(id);
                transportador.setNome(ds_nome);
                transportador.setPlaca(ds_placa);

                lista.add(transportador);
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return lista;
    }
    
    public Transportador getTransportador(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from transportador where id = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                
                Integer id = rs.getInt("id");
                String ds_transportador = rs.getString("ds_transportador");
                String ds_placa = rs.getString("ds_placa");
                
                Transportador transportador = new Transportador();

                transportador.setId(id);
                transportador.setNome(ds_transportador);
                transportador.setPlaca(ds_placa);
                

                return transportador;
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }
    
}
