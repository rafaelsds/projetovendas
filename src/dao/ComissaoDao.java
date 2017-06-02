package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Comissao;

public class ComissaoDao {         
    public void insert(Comissao comissao) {
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = ConnectionFactory.getConnection();
            
            String sql = "insert into comissao (vl_comissao,dt_inicio_vigencia,dt_fim_vigencia) values (?,?,?)";
            
            pst = con.prepareStatement(sql);
            pst.setInt(1, comissao.getValor());
            pst.setDate(2,(java.sql.Date) comissao.getDataInicio());
            pst.setDate(3,(java.sql.Date) comissao.getDataFinal());
            
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
            String sql = "delete from comissao where id = ?";
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
    
    public void update(int codigo, Comissao comissao) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update comissao set vl_comissao = ?,dt_inicio_vigencia = ?,dt_fim_vigencia = ? where id = ?";
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, comissao.getValor());
            pst.setDate(2,(java.sql.Date) comissao.getDataInicio());
            pst.setDate(3,(java.sql.Date) comissao.getDataFinal());
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
    
    public List<Comissao> getAll() throws ClassNotFoundException {
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
                java.sql.Date dataFinal = rs.getDate("dt_fim_vigencia");

                Comissao comissao = new Comissao();

                comissao.setId(id);
                comissao.setValor(valor);
                comissao.setDataInicio(dataInicio);
                comissao.setDataFinal(dataFinal);

                lista.add(comissao);
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
    
    public Comissao getComissao(int codigo) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from comissao where cod = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                
                Integer id = rs.getInt("id");
                Integer valor = rs.getInt("vl_comissao");
                java.sql.Date dataInicio = rs.getDate("dt_inicio_vigencia");
                java.sql.Date dataFinal = rs.getDate("dt_fim_vigencia");


                Comissao comissao = new Comissao();

                comissao.setId(id);
                comissao.setValor(valor);
                comissao.setDataInicio(dataInicio);
                comissao.setDataFinal(dataFinal);     
                

                return comissao;
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


