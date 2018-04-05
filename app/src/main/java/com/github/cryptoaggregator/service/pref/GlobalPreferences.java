package com.github.cryptoaggregator.service.pref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pschoffer on 2018-04-05.
 */

public class GlobalPreferences {
    private final List<String> activeCurrencies;

    protected GlobalPreferences() {
        activeCurrencies = new ArrayList<>();
    }

    public void addCurrency(String currencyId) {
        activeCurrencies.add(currencyId);
    }

    public void addCurrencies(String ... currencyIds) {
        activeCurrencies.addAll(Arrays.asList(currencyIds));
    }

    public void removeCurrency(String currencyId) {
        activeCurrencies.remove(currencyId);
    }

    public List<String> getActiveCurrencies() {
        return activeCurrencies;
    }
}
