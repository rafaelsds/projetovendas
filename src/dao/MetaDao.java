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
import model.Cep;
import model.Meta;

public class MetaDao extends DaoPadrao{
    public void insert(Meta meta) throws BancoException{
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionFactory.getConnection();

            String sql = "insert into meta (ds_meta, vl_meta, dt_inicio_vigencia, dt_final_vigencia) values (?,?,?,?)";
            

            
            pst = con.prepareStatement(sql);
            pst.setString(1, meta.getDescricao());
            pst.setInt(2, meta.getValor());
            pst.setTimestamp(3, new Timestamp(meta.getDataInicio().getTime()));
            pst.setTimestamp(4, new Timestamp(meta.getDataFinal().getTime()));
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
            String sql = "delete from meta where id = ?";
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

    public void update(int codigo, Meta meta) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update meta set ds_meta = ?, vl_meta = ?, dt_inicio_vigencia = ?, dt_final_vigencia = ?"
                    + " where id = ?";
            pst = conn.prepareStatement(sql);

            pst.setString(1, meta.getDescricao());
            pst.setInt(2, meta.getValor());
            pst.setTimestamp(3, new Timestamp(meta.getDataInicio().getTime()));
            pst.setTimestamp(4, new Timestamp(meta.getDataFinal().getTime()));
            pst.setInt(5, codigo);

            pst.execute();
            conn.commit();
        }catch(SQLException e) {  
            erro(conn, "Erro ao atualizar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
    }

    public List<Meta> getAll() throws BancoException {
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
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return lista;
    }

    public Meta getMeta(int codigo) throws BancoException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from meta where id = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

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

                return meta;
            }
        } catch(SQLException e) {  
            erro(conn, "Erro ao buscar registro ", e);
        } finally {
            finaliza(conn, pst);
        }
        return null;
    }
}
