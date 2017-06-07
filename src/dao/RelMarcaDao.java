package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Bairro;

public class RelMarcaDao {
   
    public ResultSet getAll(String descricaoP,String idP) {
        if(idP.isEmpty()){
            idP="0";
        }
        
        if(descricaoP.isEmpty()){
            descricaoP="0";
        }
        
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "select * from marca "
                    + "where (id = '"+idP
                    +"' or '"+idP+"' ='0') "
                    +" and (upper(ds_marca) like upper('%"+descricaoP+"%') "
                    +" or '"+descricaoP+"' ='0')";
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            return rs;
            
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
