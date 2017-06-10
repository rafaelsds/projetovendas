package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Bairro;

public class RelatorioDefaultDao {
    /* Passar por parâmetro, o nome da tabela que deseja consultar, nome de atributo do id e descrição.
    Passar também caso desejar, o parâmetro de ID e Descrição
    */    
    public ResultSet getAll(String tabela, String v1, String v2, String p1,String p2) {
        if(p1.isEmpty()){
            p1="0";
        }
        
        if(p2.isEmpty()){
            p2="0";
        }
        
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            
            String sql = "select "+v1+" v1, "+v2+" v2"
            +" from "+tabela.trim()        
            +" where ("+v1+" = '"+p1
            +"' or '"+p1+"' ='0') "
            +" and (upper("+v2+") like upper('%"+p2+"%') "
            +" or '"+p2+"' ='0')";
            
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
    
    public ResultSet getAll(String tabela, String v1, String v2, String v3, String p1,String p2) {
        if(p1.isEmpty()){
            p1="0";
        }
        
        if(p2.isEmpty()){
            p2="0";
        }
        
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectionFactory.getConnection();
            
            String sql = "select "+v1+" v1, "+v2+" v2, "+v3+" v3"
            +" from "+tabela.trim()        
            +" where ("+v1+" = '"+p1
            +"' or '"+p1+"' ='0') "
            +" and (upper("+v2+") like upper('%"+p2+"%') "
            +" or '"+p2+"' ='0')";
            
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
