package service;

import dao.CurrencyDao;
import exeption.CurrencyAlreadyExistsException;
import exeption.CurrencyNotFoundException;
import model.Currency;

import java.sql.SQLException;
import java.util.List;

public class CurrencyService {
    CurrencyDao currencyDao = new CurrencyDao();

    public List<Currency> getAllCurrencies() {
        return currencyDao.findAll();
    }

    public Currency getCurrencyCode(String CurrencyCode) {
        return currencyDao.findCurrencyCode(CurrencyCode);
    }

    public Currency insertCurrency(Currency currency) throws SQLException {
        try {
            currencyDao.findCurrencyCode(currency.getCode());
        } catch (CurrencyNotFoundException e) {
            Currency currencyResoult = currencyDao.insertCurrency(currency);
            return currencyResoult;
        }
        throw new CurrencyAlreadyExistsException();
    }
}
