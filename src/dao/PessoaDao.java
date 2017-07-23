package dao;

import connection.ConnectionFactory;
import exceptions.BancoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pessoa;

public class PessoaDao extends DaoPadrao{
    
    public void insert(Pessoa pessoa)throws BancoException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionFactory.getConnection();
            
            //String sql = "insert into pessoa(ds_nome, ds_endereco, nr_endereco, id_bairro, id_municipio, id_uf, id_cep, nr_telefone, ds_email) values(?,?,?,?,?,?,?,?,?)";
            String sql = "insert into pessoa(ds_nome, ds_endereco, nr_endereco, nr_telefone, ds_email, id_municipio, id_cep, id_bairro,id_uf) values(?,?,?,?,?,?,?,?,?)";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, pessoa.getNome());
            pst.setString(2, pessoa.getEndereco());
            pst.setString(3, pessoa.getNumeroEndereco());
            pst.setString(4, pessoa.getTelefone());
            pst.setString(5, pessoa.getEmail());
            pst.setInt(6, pessoa.getIdMunicipio());
            pst.setInt(7, pessoa.getIdCep());
            pst.setInt(8, pessoa.getIdBairro());
            pst.setInt(9, pessoa.getIdUf());
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
            String sql = "delete from pessoa where id = ?";
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
    
    public void update(Pessoa pessoa) throws BancoException, SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update pessoa set ds_nome = ?, ds_endereco = ?, nr_endereco = ?, id_bairro = ?, id_municipio = ?, id_cep = ?, nr_telefone = ?, ds_email = ?, id_uf=?"
                    + " where id = ?";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, pessoa.getNome());
            pst.setString(2, pessoa.getEndereco());
            pst.setString(3, pessoa.getNumeroEndereco());
            pst.setString(4, pessoa.getIdBairro().toString());
            pst.setString(5, pessoa.getIdMunicipio().toString());
          //  pst.setString(6, pessoa.getIdUf().toString());
            pst.setString(6, pessoa.getIdCep().toString());
            pst.setString(7, pessoa.getTelefone());
            pst.setString(8, pessoa.getEmail());
            pst.setInt(9, pessoa.getIdUf());
            pst.setInt(10, pessoa.getId());

            pst.execute();
            conn.commit();
        } catch(SQLException e) {  
            erro(conn, "Erro ao atualizar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    public List<Pessoa> getAll() throws BancoException {

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
                pessoa.setNome(rs.getString("ds_nome"));
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
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return lista;
    }
    
    public Pessoa getPessoa(int codigo) throws BancoException {
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
                pessoa.setNome(rs.getString("ds_nome"));
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
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }
}
