package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    
    public Connection getConnection() {
        Connection con = null;
        
        try {
            Class.forName("driver mysql");
            con = DriverManager.getConnection("nome do banco", "user", "password");
        } catch (Exception e) {
            System.out.println("Erro ao conectar o banco de dados!");
        }
        
        return con;
    }
}
