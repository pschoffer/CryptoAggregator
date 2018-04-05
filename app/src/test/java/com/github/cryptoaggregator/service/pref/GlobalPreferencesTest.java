package com.github.cryptoaggregator.service.pref;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by pschoffer on 2018-04-05.
 */
public class GlobalPreferencesTest {

    private static final String CURRENCY_1 = "ETH";
    private static final String CURRENCY_2 = "BTC";
    private static final String CURRENCY_3 = "DEO";

    @Test
    public void testAddCurrency() {
        final GlobalPreferences preferences = new GlobalPreferences();
        preferences.addCurrency(CURRENCY_1);
        preferences.addCurrency(CURRENCY_2);

        final List<String> activeCurrencies = preferences.getActiveCurrencies();

        assertEquals(2, activeCurrencies.size());
        assertEquals(CURRENCY_1, activeCurrencies.get(0));
        assertEquals(CURRENCY_2, activeCurrencies.get(1));
    }
    @Test
    public void testRemoval() {
        final GlobalPreferences preferences = new GlobalPreferences();
        preferences.addCurrencies(CURRENCY_1, CURRENCY_2, CURRENCY_3);

        preferences.removeCurrency(CURRENCY_2);

        final List<String> activeCurrencies = preferences.getActiveCurrencies();
        assertEquals(2, activeCurrencies.size());
        assertEquals(CURRENCY_1, activeCurrencies.get(0));
        assertEquals(CURRENCY_3, activeCurrencies.get(1));
    }
}