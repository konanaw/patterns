package by.konanaw.patterns.factory;

import java.util.Arrays;
import java.util.List;

public class FactoryMain {

    public static void main(String args[]) {

        String country = "India";
        Currency rupee = CurrencyFactory.createCurrency(country);
        System.out.println(rupee.getClass().getName() + ": " + rupee.getSymbol());

        Currency currency = CurrencyFactory.createCurrency(SGDDollar.class);
        System.out.println(currency.getClass().getName() + ": " + currency.getSymbol());

        System.out.println("-----");
        List<Class<? extends Currency>> currencyClasses = Arrays.asList(Rupee.class, SGDDollar.class, USDollar.class);
        for (Class<? extends Currency> clazz : currencyClasses) {
            currency = CurrencyFactory.createCurrency(clazz);
            System.out.println(currency.getClass().getName() + ": " + currency.getSymbol());
        }
    }
}
