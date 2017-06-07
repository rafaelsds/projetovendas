package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pessoa;

public class PessoaDao {
    
    public void insert(Pessoa pessoa) {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionFactory.getConnection();
            
            //String sql = "insert into pessoa(ds_nome, ds_endereco, nr_endereco, id_bairro, id_municipio, id_uf, id_cep, nr_telefone, ds_email) values(?,?,?,?,?,?,?,?,?)";
            String sql = "insert into pessoa(ds_nome, ds_endereco, nr_endereco, nr_telefone, ds_email) values(?,?,?,?,?)";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, pessoa.getNome());
            pst.setString(2, pessoa.getEndereco());
            pst.setString(3, pessoa.getNumeroEndereco());
            pst.setString(4, pessoa.getTelefone());
            pst.setString(5, pessoa.getEmail());
            
            pst.execute();
            con.commit();
            
        } catch (Exception e) {
            System.out.println("ERRO1: " + e.getMessage());

            if (con != null) {
                try {
                    con.rollback();
                } catch (Exception ex) {
                    System.out.println("ERRO2: " + ex.getMessage());
                }
            }
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    System.out.println("ERRO3: " + e.getMessage());
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.out.println("ERRO4: " + e.getMessage());
                }
            }
        }
    }
    
    public void delete(int codigo) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "delete from pessoa where id = ?";
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
    
    public void update(Pessoa pessoa) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update pessoa set ds_nome = ?, ds_endereco = ?, nr_endereco = ?, id_bairro = ?, id_municipio = ?, id_uf = ?, id_cep = ?, nr_telefone = ?, ds_email = ?"
                    + " where id = ?";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, pessoa.getNome());
            pst.setString(2, pessoa.getEndereco());
            pst.setString(3, pessoa.getNumeroEndereco());
            pst.setString(4, pessoa.getIdBairro().toString());
            pst.setString(5, pessoa.getIdMunicipio().toString());
            pst.setString(6, pessoa.getIdUf().toString());
            pst.setString(7, pessoa.getIdCep().toString());
            pst.setString(8, pessoa.getTelefone());
            pst.setString(9, pessoa.getEmail());
            pst.setInt(10, pessoa.getId());

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
    
    public List<Pessoa> getAll() throws ClassNotFoundException {

        List<Pessoa> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from pessoa";
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                Pessoa pessoa = new Pessoa();

                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("ds_marca"));
                pessoa.setEndereco(rs.getString("ds_endereco"));
                pessoa.setEmail(rs.getString("ds_email"));
                pessoa.setIdBairro(rs.getInt("id_bairro"));
                pessoa.setIdCep(rs.getInt("id_cep"));
                pessoa.setIdMunicipio(rs.getInt("id_municipio"));
                pessoa.setIdUf(rs.getInt("id_uf"));
                pessoa.setNumeroEndereco(rs.getString("nr_endereco"));
                pessoa.setTelefone(rs.getString("nr_telefone"));
           
                lista.add(pessoa);
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
    
    public Pessoa getPessoa(int codigo) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from pessoa where id = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                
     
                Pessoa pessoa = new Pessoa();
                
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("ds_marca"));
                pessoa.setEndereco(rs.getString("ds_endereco"));
                pessoa.setEmail(rs.getString("ds_email"));
                pessoa.setIdBairro(rs.getInt("id_bairro"));
                pessoa.setIdCep(rs.getInt("id_cep"));
                pessoa.setIdMunicipio(rs.getInt("id_municipio"));
                pessoa.setIdUf(rs.getInt("id_uf"));
                pessoa.setNumeroEndereco(rs.getString("nr_endereco"));
                pessoa.setTelefone(rs.getString("nr_telefone"));

                return pessoa;
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
