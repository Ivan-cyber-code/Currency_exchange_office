package dao;

import model.Currency;

import java.util.List;

public class CurrencyDaoTest {
    public static void main(String[] args) {
        CurrencyDao currencyDao = new CurrencyDao();
        List<Currency> currencies = currencyDao.findAll();
        System.out.println(String.format("Найдено валют : %s",currencies.size()));
        for (Currency currency:currencies){
            System.out.println(currency);
        }

    }
}
