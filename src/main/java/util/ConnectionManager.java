package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/currency_exchange";
    private static final String USER = "postgres";
    private static final String PASSWORD = "201002607497";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            throw new RuntimeException("PostgreSQL driver not found. Check pom.xml!");
        }
    }

    private ConnectionManager() {
    }

    public static Connection open() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
