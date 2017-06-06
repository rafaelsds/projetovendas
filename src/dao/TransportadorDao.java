package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Transportador;

public class TransportadorDao {
     public void insert(Transportador transportador) {
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
            String sql = "delete from transportador where id = ?";
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
    
    public void update(int codigo, Transportador transportador) throws ClassNotFoundException, SQLException {
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
    
    public List<Transportador> getAll() throws ClassNotFoundException {
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
    
    public Transportador getTransportador(int codigo) throws ClassNotFoundException {
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
