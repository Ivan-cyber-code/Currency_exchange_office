package dao;

public class CurrencyDaoTest {
    public static void main(String[] args) {
        CurrencyDao currencyDao = new CurrencyDao();
//        List<Currency> currencies = currencyDao.findAll();
//        System.out.println(String.format("Найдено валют : %s",currencies.size()));
//        for (Currency currency:currencies){
//            System.out.println(currency);
//        }
//        try {
//            System.out.println(currencyDao.insertCurrency(new Currency(6,"AFN","Афганский","؋")));
//        } catch (SQLException e) {
//            System.out.println("Error: invalid data for insertion into the table");
//        }
        System.out.println(currencyDao.getCurrencyCode("CAr"));
    }
}
