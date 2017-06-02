package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Cep;
import model.Meta;

public class MetaDao {
    public void insert(Meta meta) {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionFactory.getConnection();

            String sql = "insert into meta (ds_meta, vl_meta, dt_inicio_vigencia, dt_final_vigencia) values (?,?,?,?)";
            

            
            pst = con.prepareStatement(sql);
            pst.setString(1, meta.getDescricao());
            pst.setInt(2, meta.getValor());
            pst.setTimestamp(3, new Timestamp(meta.getDataFinal().getTime()));
            pst.setTimestamp(4, new Timestamp(meta.getDataInicio().getTime()));

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
            String sql = "delete from meta where id = ?";
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

    public void update(int codigo, Meta meta) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update meta set ds_meta = ?, vl_meta = ?, dt_inicio_vigencia = ?, dt_final_vigencia = ?"
                    + " where id = ?";
            pst = conn.prepareStatement(sql);

            pst.setString(1, meta.getDescricao());
            pst.setInt(2, meta.getValor());
            pst.setDate(3, (java.sql.Date) meta.getDataInicio());
            pst.setDate(4, (java.sql.Date) meta.getDataFinal());
            pst.setInt(5, codigo);

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

    public List<Meta> getAll() throws ClassNotFoundException {
        List<Meta> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from meta";
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Integer id = rs.getInt("id");
                String ds_meta = rs.getString("ds_meta");
                Integer vl_meta = rs.getInt("vl_meta");
                Date data_inicio = rs.getDate("dt_inicio_vigencia");
                Date data_final = rs.getDate("dt_final_vigencia");

                Meta meta = new Meta();

                meta.setId(id);
                meta.setDescricao(ds_meta);
                meta.setValor(vl_meta);
                meta.setDataInicio(data_inicio);
                meta.setDataFinal(data_final);

                lista.add(meta);
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

    public Cep getCep(int codigo) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from cep where cod = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                Integer id = rs.getInt("id");
                String nr_cep = rs.getString("nr_cpf");

                Cep cep = new Cep();

                cep.setId(id);
                cep.setCep(nr_cep);

                return cep;
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
