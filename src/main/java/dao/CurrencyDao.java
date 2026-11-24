package dao;

import exeption.CurrencyNotFoundException;
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
            if (resultSet.next()) {
                Currency currency = new Currency(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                return currency;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new CurrencyNotFoundException();
    }

    public Currency insertCurrency(Currency currency) throws SQLException {
        Currency currencyResult = null;
        String sql = "insert into currency_exchange.public.currencies (code, fullname, sign) values (?,?,?)";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, currency.getCode());
            preparedStatement.setString(2, currency.getFullName());
            preparedStatement.setString(3, currency.getSign());
            preparedStatement.executeUpdate();
            ResultSet newCurrency = preparedStatement.getGeneratedKeys();
            if (newCurrency.next()) {
                currencyResult = new Currency(newCurrency.getInt(1), newCurrency.getString(2), newCurrency.getString(3), newCurrency.getString(4));
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return currencyResult;
    }
}