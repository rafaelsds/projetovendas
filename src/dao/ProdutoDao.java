package dao;

import connection.ConnectionFactory;
import exceptions.BancoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDao extends DaoPadrao{
    
    public void insert(Produto produto)throws BancoException  {
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = ConnectionFactory.getConnection();
            
            String sql = "insert into produto(ds_produto,ds_referencia, cod_barra, id_marca, "
                    + "qt_estoque, id_grupo, vl_custo, vl_venda, vl_prazo, nr_ncm, nr_cst, tipo_venda)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            pst = con.prepareStatement(sql);

            pst.setString(1, produto.getNome());
            pst.setString(2, produto.getReferencia());
            pst.setString(3, produto.getCodigoBarras());
            pst.setInt(4, produto.getIdMarca());
            pst.setInt(5, produto.getEstoque());
            pst.setInt(6, produto.getIdGrupo());
            pst.setInt(7, produto.getPrecoCusto());
            pst.setInt(8, produto.getPrecoVenda());
            pst.setInt(9, produto.getPrecoPrazo());
            pst.setString(10, produto.getNcm());
            pst.setString(11, produto.getCst());
            pst.setInt(12, produto.getTipoDeVenda());
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
            String sql = "delete from produto where id = ?";
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
    
    public void update(int codigo, Produto produto) throws BancoException, SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update produto set ds_produto = ?, ds_referencia = ?, "
                    + "cod_barra = ?, id_marca = ?, qt_estoque = ?, id_grupo = ?, vl_custo = ?, "
                    + "vl_venda = ?, vl_prazo = ?, nr_ncm = ?, nr_cst = ?, tipo_venda = ? where id = ?";
            
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, produto.getNome());
            pst.setString(2, produto.getReferencia());
            pst.setString(3, produto.getCodigoBarras());
            pst.setInt(4, produto.getIdMarca());
            pst.setInt(5, produto.getEstoque());
            pst.setInt(6, produto.getIdGrupo());
            pst.setInt(7, produto.getPrecoCusto());
            pst.setInt(8, produto.getPrecoVenda());
            pst.setInt(9, produto.getPrecoPrazo());
            pst.setString(10, produto.getNcm());
            pst.setString(11, produto.getCst());
            pst.setInt(12, produto.getTipoDeVenda());
            pst.setInt(13, codigo);
            
            pst.execute();
            conn.commit();
        } catch(SQLException e) {  
            erro(conn, "Erro ao atualizar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    public List<Produto> getAll() throws BancoException {
        List<Produto> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from produto";
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("ds_produto"));
                produto.setReferencia(rs.getString("ds_referencia"));
                produto.setCodigoBarras(rs.getString("cod_barra"));
                produto.setIdMarca(rs.getInt("id_marca"));
                produto.setEstoque(rs.getInt("qt_estoque"));
                produto.setIdGrupo(rs.getInt("id_grupo"));
                produto.setPrecoCusto(rs.getInt("vl_custo"));
                produto.setPrecoVenda(rs.getInt("vl_venda"));
                produto.setPrecoPrazo(rs.getInt("vl_prazo"));
                produto.setNcm(rs.getString("nr_ncm"));
                produto.setCst(rs.getString("nr_cst"));
                produto.setTipoDeVenda(rs.getInt("tipo_venda"));
                

                lista.add(produto);
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return lista;
    }
    
    public List<Produto> getProdutos(Integer codigo, Integer valor, String descricao, Integer estoque) throws BancoException {
        List<Produto> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from produto where "
                    + " (id = ? or ? = 0) "
                    + " and (upper(ds_produto) like ? or ? = '') "
                    + " and (vl_venda = ? or ? = 0) "
                    + " and qt_estoque >= ?";
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, codigo);
            pst.setInt(2, codigo);
             pst.setString(3, "%"+descricao.toUpperCase().trim()+"%");
            pst.setString(4, descricao.toUpperCase().trim());
            pst.setInt(5, valor);
            pst.setInt(6, valor);
            pst.setInt(7, estoque);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                
                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("ds_produto"));
                produto.setReferencia(rs.getString("ds_referencia"));
                produto.setCodigoBarras(rs.getString("cod_barra"));
                produto.setIdMarca(rs.getInt("id_marca"));
                produto.setEstoque(rs.getInt("qt_estoque"));
                produto.setIdGrupo(rs.getInt("id_grupo"));
                produto.setPrecoCusto(rs.getInt("vl_custo"));
                produto.setPrecoVenda(rs.getInt("vl_venda"));
                produto.setPrecoPrazo(rs.getInt("vl_prazo"));
                produto.setNcm(rs.getString("nr_ncm"));
                produto.setCst(rs.getString("nr_cst"));
                produto.setTipoDeVenda(rs.getInt("tipo_venda"));
                

                lista.add(produto);
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return lista;
    }
    
    public Produto getProduto(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from produto where id = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {              
            
                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("ds_produto"));
                produto.setReferencia(rs.getString("ds_referencia"));
                produto.setCodigoBarras(rs.getString("cod_barra"));
                produto.setIdMarca(rs.getInt("id_marca"));
                produto.setEstoque(rs.getInt("qt_estoque"));
                produto.setIdGrupo(rs.getInt("id_grupo"));
                produto.setPrecoCusto(rs.getInt("vl_custo"));
                produto.setPrecoVenda(rs.getInt("vl_venda"));
                produto.setPrecoPrazo(rs.getInt("vl_prazo"));
                produto.setNcm(rs.getString("nr_ncm"));
                produto.setCst(rs.getString("nr_cst"));
                produto.setTipoDeVenda(rs.getInt("tipo_venda"));
                
                return produto;
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }
}
