package dao;

import connection.ConnectionFactory;
import exceptions.BancoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cep;

public class CepDao extends DaoPadrao{

    public void insert(Cep cep)throws BancoException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionFactory.getConnection();

            String sql = "insert into cep (nr_cep) values (?)";

            pst = con.prepareStatement(sql);
            pst.setString(1, cep.getCep());

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
            String sql = "delete from cep where id = ?";
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

    public void update(int codigo, Cep cep) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update cep set nr_cep = ? where id = ?";
            pst = conn.prepareStatement(sql);

            pst.setString(1, cep.getCep());
            pst.setInt(2, codigo);

            pst.execute();
            conn.commit();
        } catch(SQLException e) {  
            erro(conn, "Erro ao atualizar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
    }

    public List<Cep> getAll() throws BancoException {
        List<Cep> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from cep";
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Integer id = rs.getInt("id");
                String nr_cep = rs.getString("nr_cep");

                Cep cep = new Cep();

                cep.setId(id);
                cep.setCep(nr_cep);

                lista.add(cep);
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return lista;
    }

    public Cep getCep(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from cep where id = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                Integer id = rs.getInt("id");
                String nr_cep = rs.getString("nr_cep");

                Cep cep = new Cep();

                cep.setId(id);
                cep.setCep(nr_cep);

                return cep;
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }
}
