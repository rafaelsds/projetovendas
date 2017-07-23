/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import exceptions.BancoException;

/**
 *
 * @author Rafael
 */
public class DaoPadrao {
    protected void finaliza(Connection conn, PreparedStatement ps) throws BancoException {
        if( ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new BancoException(e.getMessage());
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new BancoException(e.getMessage());
            }
        }
    }
    
    protected void erro(Connection conn, String mensagem, Exception e) throws BancoException {
        if(conn != null){
            try {
                conn.rollback();
            } catch (SQLException ex) {
            }
        }
        if (e != null)
            mensagem += ":"+e.getMessage();
        throw new BancoException(mensagem);
    }
}
