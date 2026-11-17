package dao;

import model.Currency;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CurrencyDao {
    public List<Currency> findAll() {
        List<Currency> currencies = new ArrayList<>();
        try (
                Connection connection = ConnectionManager.open();
                Statement statement = connection.createStatement();
                ResultSet sql = statement.executeQuery("select * from currency_exchange.public.currencies")) {
            while (sql.next()) {
                Currency currency = new Currency(sql.getInt(1), sql.getString(2), sql.getString(3), sql.getString(4));
                currencies.add(currency);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error find all list");
        }
        return currencies;
    }
}

