package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.PessoaFisica;
import model.Sexo;

public class PessoaFisicaDao {
    public void insert(PessoaFisica pessoaFisica) {
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = ConnectionFactory.getConnection();
            
            String sql = "insert into pessoa_fisica (nr_cpf, nr_rg, sexo, dt_nascimento) values (?,?,?,?)";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, pessoaFisica.getCpf());
            
            pst.setString(2, pessoaFisica.getRg());
            pst.setString(3, pessoaFisica.getSexo().toString());
            pst.setTimestamp(4, new Timestamp(pessoaFisica.getDataNascimento().getTime()));
            
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
            String sql = "delete from pessoa_fisica where id = ?";
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
    
    public void update(int codigo, PessoaFisica pessoaFisica) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update pessoa_fisica set nr_rg = ?,"
                    + " nr_cpf = ?,"
                    + " sexo = ?,"
                    + " dt_nascimento = ?"
                    + " where id = ?";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, pessoaFisica.getCpf());
            pst.setString(2, pessoaFisica.getRg());
            pst.setString(3, pessoaFisica.getSexo().toString());
            pst.setTimestamp(4, new Timestamp(pessoaFisica.getDataNascimento().getTime()));
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
    
    public List<PessoaFisica> getAll() throws ClassNotFoundException {
        List<PessoaFisica> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from pessoa_fisica";
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
          
                PessoaFisica pessoaFisica = new PessoaFisica();

                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setCpf(rs.getString("nr_cpf"));
                pessoaFisica.setRg(rs.getString("nr_rg"));
                
                if(rs.getString("sexo").toUpperCase().equals("FEMININO")){    
                    pessoaFisica.setSexo((Sexo.FEMININO));
                }else
                {
                    pessoaFisica.setSexo((Sexo.MASCULINO));
                }
                
                pessoaFisica.setDataNascimento(rs.getString("dt_nascimento"));
                lista.add(pessoaFisica);
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
    
    public PessoaFisica getPessoaFisica(int codigo) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from pessoa_fisica where cod = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                
                Integer id = rs.getInt("id");
                String ds_pessoaFisica = rs.getString("ds_pessoaFisica");
                
                PessoaFisica pessoaFisica = new PessoaFisica();
                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setCpf(rs.getString("nr_cpf"));
                pessoaFisica.setRg(rs.getString("nr_rg"));
                
                if(rs.getString("sexo").toUpperCase().equals("FEMININO")){    
                    pessoaFisica.setSexo((Sexo.FEMININO));
                }else
                {
                    pessoaFisica.setSexo((Sexo.MASCULINO));
                }
                pessoaFisica.setDataNascimento(rs.getString("dt_nascimento"));
                
                return pessoaFisica;
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
