package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {
    public static void main(String[] args) {
        try (Connection connection = ConnectionManager.open();
             var statement = connection.createStatement();
             var sql = statement.executeQuery("select * from currency_exchange.public.currencies")) {
            System.out.println("Подключение успешно!");
            System.out.println("ID | Code | FullName | Sign");
            System.out.println("---|------|----------|----|");
            while (sql.next()){
                System.out.println(sql.getInt(1)+" | "+sql.getString(2)+" | "+sql.getString(3)+" | "+sql.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("Connection error: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
