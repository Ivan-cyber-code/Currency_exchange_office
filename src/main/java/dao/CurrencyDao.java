package dao;

import model.Currency;
import util.ConnectionManager;

import java.sql.*;
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

    public Currency findCurrencyCode(String currenceCode) {
        String sql = "select * from currency_exchange.public.currencies c where c.code = ?";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, currenceCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Currency currency = new Currency(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                return currency;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException();
    }
}