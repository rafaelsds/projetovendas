/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionFactory;
import exceptions.BancoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Vendedor;

/**
 *
 * @author comp16
 */
public class VendedorDao extends DaoPadrao{
    
    public List<Vendedor> getAll() throws BancoException {
        List<Vendedor> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select a.*,b.ds_nome from vendedor a left join pessoa b on(a.id_pessoa = b.id)";
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Vendedor v = new Vendedor();

                v.setId(rs.getInt("id"));
                v.setIdPessoa(rs.getInt("id_pessoa"));
                v.setNomeVendedor(rs.getString("ds_nome"));
                v.setIdMeta(rs.getInt("id_meta"));
                v.setIdComissao(rs.getInt("id_comissao"));

                lista.add(v);
            }
        } catch(SQLException e) {
            erro(conn, "Erro ao buscar registro", e);
        } finally {
            finaliza(conn, pst);
        }
        return lista;
    }
    
    
    public Vendedor getVendedor(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select a.*,b.ds_nome from vendedor a left join pessoa b on(a.id_pessoa = b.id) where a.id = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                Vendedor v = new Vendedor();

                v.setId(rs.getInt("id"));
                v.setIdPessoa(rs.getInt("id_pessoa"));
                v.setNomeVendedor(rs.getString("ds_nome"));
                v.setIdMeta(rs.getInt("id_meta"));
                v.setIdComissao(rs.getInt("id_comissao"));

                return v;
            }
        } catch(SQLException e) {
            erro(conn, "Erro ao obter registro", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }

    
    public void update(int codigo, Vendedor v) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update vendedor set id_pessoa = ?, id_meta = ?, id_comissao = ? "
                    + " where id = ?";
            pst = conn.prepareStatement(sql);

            pst.setInt(1, v.getIdPessoa());
            pst.setInt(2, v.getIdMeta());
            pst.setInt(3, v.getIdComissao());
            pst.setInt(4, codigo);

            pst.execute();
            conn.commit();
        } catch(SQLException e) {
            erro(conn, "Erro ao atualizar registro", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    public void delete(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "delete from vendedor where id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            pst.execute();

            conn.commit();
        } catch(SQLException e) {
            erro(conn, "Erro ao excluir registro", e);
        } finally {
            finaliza(conn, pst);
        }
    }
    
    
    public void insert(Vendedor v)throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = ConnectionFactory.getConnection();

            String sql = "insert into vendedor (id_pessoa, id_meta, id_comissao) values (?,?,?)";
            

            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, v.getIdPessoa());
            pst.setInt(2, v.getIdMeta());
            pst.setInt(3, v.getIdComissao());
            pst.execute();
            conn.commit();

        } catch(SQLException e) {
            erro(conn, "Erro ao inserir registro", e);
        } finally {
            finaliza(conn, pst);
        }
    }
}
