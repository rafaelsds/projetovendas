package dao;

import connection.ConnectionFactory;
import exceptions.BancoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.NotaFiscal;

public class NotaFiscalDao extends DaoPadrao{

    public void insert(NotaFiscal notaFiscal) throws BancoException{
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionFactory.getConnection();

            String sql = "insert into nota_fiscal (id_venda, nr_cfop, id_transportador) values (?, ?, ?)";

            pst = con.prepareStatement(sql);
            pst.setInt(1, notaFiscal.getIdVenda());
            pst.setString(2, notaFiscal.getCfop());
            pst.setInt(3, notaFiscal.getIdTransportador());

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
            String sql = "delete from nota_fiscal where id = ?";
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

    public void update(int codigo, NotaFiscal notaFiscal) throws BancoException, SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update nota_fiscal set id_venda = ?, nr_cfop = ?, id_transportador = ? where id = ?";
            pst = conn.prepareStatement(sql);

            pst.setInt(1, notaFiscal.getIdVenda());
            pst.setString(2, notaFiscal.getCfop());
            pst.setInt(3, notaFiscal.getIdTransportador());
            pst.setInt(4, codigo);

            pst.execute();
            conn.commit();
        } catch(SQLException e) {  
            erro(conn, "Erro ao atualizar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
    }

    public List<NotaFiscal> getAll() throws BancoException {
        List<NotaFiscal> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from nota_fiscal";
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Integer id = rs.getInt("id");
                Integer id_venda = rs.getInt("id_venda");
                String nr_cfop = rs.getString("nr_cfop");
                Integer id_trans = rs.getInt("id_transportador");
                
                NotaFiscal notaFiscal = new NotaFiscal();
                
                notaFiscal.setIdTransportador(id_trans);
                notaFiscal.setIdVenda(id_venda);
                notaFiscal.setId(id);
                notaFiscal.setCfop(nr_cfop);

                lista.add(notaFiscal);
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return lista;
    }

    public NotaFiscal getNotaFiscal(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from nota_fiscal where cod = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                Integer id = rs.getInt("id");
                Integer id_venda = rs.getInt("id_venda");
                String nr_cfop = rs.getString("nr_cfop");
                Integer id_trans = rs.getInt("id_transportador");
                
                NotaFiscal notaFiscal = new NotaFiscal();
                
                notaFiscal.setId(id);
                notaFiscal.setIdTransportador(id_trans);
                notaFiscal.setIdVenda(id_venda);
                notaFiscal.setCfop(nr_cfop);

                return notaFiscal;
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }

}
