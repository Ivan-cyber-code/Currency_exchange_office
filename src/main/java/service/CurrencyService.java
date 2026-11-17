package service;

import dao.CurrencyDao;
import model.Currency;

import java.util.List;

public class CurrencyService {
    CurrencyDao currencyDao = new CurrencyDao();

    public List<Currency> getAllCurrencies(){
        return currencyDao.findAll();
    }
}
