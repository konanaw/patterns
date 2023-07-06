package by.konanaw.patterns.factory;

import java.lang.reflect.Constructor;

public class CurrencyFactory {

    /**
     * Первый способ. Необходимо вводить дополнительную переменную, или enum для сопоставления с классом.
     * Допзатраты на добавление нового класса.
     * @param country
     * @return
     */
    public static Currency createCurrency(String country) {
        if (country.equalsIgnoreCase("India")) {
            return new Rupee();
        } else if (country.equalsIgnoreCase("Singapore")) {
            return new SGDDollar();
        } else if (country.equalsIgnoreCase("US")) {
            return new USDollar();
        }
        throw new IllegalArgumentException("No such currency");
    }

    /**
     * Второй способ основанный на reflection.
     *
     * @param currencyClass
     * @return
     */
    public static Currency createCurrency(Class<? extends Currency> currencyClass) {
        try {
            Constructor<?> constructor = currencyClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            return (Currency) constructor.newInstance();
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

//    private ClientHttpRequestFactory createRequestFactory(Class<? extends ClientHttpRequestFactory> requestFactory) {
//        try {
//            Constructor<?> constructor = requestFactory.getDeclaredConstructor();
//            constructor.setAccessible(true);
//            return (ClientHttpRequestFactory) constructor.newInstance();
//        }
//        catch (Exception ex) {
//            throw new IllegalStateException(ex);
//        }
//    }
}
