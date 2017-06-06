package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.NotaFiscal;

public class NotaFiscalDao {

    public void insert(NotaFiscal notaFiscal) {
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

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());

            if (con != null) {
                try {
                    con.rollback();
                } catch (Exception ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    System.out.println("ERRO: " + e.getMessage());
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.out.println("ERRO: " + e.getMessage());
                }
            }
        }
    }

    public void delete(int codigo) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "delete from nota_fiscal where id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            pst.execute();

            conn.commit();
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }

        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
        }
    }

    public void update(int codigo, NotaFiscal notaFiscal) throws ClassNotFoundException, SQLException {
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
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }

        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
        }
    }

    public List<NotaFiscal> getAll() throws ClassNotFoundException {
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
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
        }
        return lista;
    }

    public NotaFiscal getNotaFiscal(int codigo) throws ClassNotFoundException {
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
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
        }
        return null;
    }

}
