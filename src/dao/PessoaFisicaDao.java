package dao;

import connection.ConnectionFactory;
import exceptions.BancoException;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.PessoaFisica;
import model.Sexo;

public class PessoaFisicaDao extends DaoPadrao{
    public void insert(PessoaFisica pessoaFisica) throws BancoException {
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = ConnectionFactory.getConnection();
            
            String sql = "insert into pessoa_fisica (nr_cpf, nr_rg, sexo, dt_nascimento,id_pessoa) values (?,?,?,?,?)";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, pessoaFisica.getCpf());
            pst.setString(2, pessoaFisica.getRg());
            pst.setString(3, pessoaFisica.getSexo().toString());
            pst.setTimestamp(4, new Timestamp(pessoaFisica.getDataNascimento().getTime()));
            pst.setInt(5, pessoaFisica.getIdPessoa());
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
            String sql = "delete from pessoa_fisica where id = ?";
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
    
    public void update(int codigo, PessoaFisica pessoaFisica) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update pessoa_fisica set nr_rg = ?,"
                    + " nr_cpf = ?,"
                    + " sexo = ?,"
                    + " dt_nascimento = ?,"
                    + " id_pessoa = ?"
                    + " where id = ?";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, pessoaFisica.getCpf());
            pst.setString(2, pessoaFisica.getRg());
            pst.setString(3, pessoaFisica.getSexo().toString());
            pst.setTimestamp(4, new Timestamp(pessoaFisica.getDataNascimento().getTime()));
            pst.setInt(5, pessoaFisica.getIdPessoa());
            pst.setInt(6, codigo);

            pst.execute();
            conn.commit();
        }  catch(SQLException e) {  
            erro(conn, "Erro ao atualizar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    public List<PessoaFisica> getAll() throws BancoException {
        List<PessoaFisica> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select a.*, b.ds_nome from pessoa_fisica a join pessoa b on(b.id = a.id_pessoa)";
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
          
                PessoaFisica pessoaFisica = new PessoaFisica();

                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setIdPessoa(rs.getInt("id_pessoa"));
                pessoaFisica.setCpf(rs.getString("nr_cpf"));
                pessoaFisica.setRg(rs.getString("nr_rg"));
                pessoaFisica.setNomePessoa(rs.getString("ds_nome"));
                
                if(rs.getString("sexo").toUpperCase().equals("FEMININO")){    
                    pessoaFisica.setSexo((Sexo.FEMININO));
                }else
                {
                    pessoaFisica.setSexo((Sexo.MASCULINO));
                }
                
                pessoaFisica.setDataNascimento(rs.getDate("dt_nascimento"));
                lista.add(pessoaFisica);
            }
        }  catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return lista;
    }
    
    public PessoaFisica getPessoaFisica(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select a.*, b.ds_nome from pessoa_fisica a join pessoa b on(b.id = a.id_pessoa) where a.id = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                
                PessoaFisica pessoaFisica = new PessoaFisica();
                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setIdPessoa(rs.getInt("id_pessoa"));
                pessoaFisica.setCpf(rs.getString("nr_cpf"));
                pessoaFisica.setRg(rs.getString("nr_rg"));
                pessoaFisica.setNomePessoa(rs.getString("ds_nome"));
                
                if(rs.getString("sexo").toUpperCase().equals("FEMININO")){    
                    pessoaFisica.setSexo((Sexo.FEMININO));
                }else
                {
                    pessoaFisica.setSexo((Sexo.MASCULINO));
                }
                
                pessoaFisica.setDataNascimento(rs.getDate("dt_nascimento"));
                
                return pessoaFisica;
            }
        }  catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }
}
