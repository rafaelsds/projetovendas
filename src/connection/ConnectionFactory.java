package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    public Connection getConnection() {
        Connection con = null;
        
        try {
            Class.forName("driver oracle");
            con = DriverManager.getConnection("nome do banco", "user", "password");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        return con;
    }
}
