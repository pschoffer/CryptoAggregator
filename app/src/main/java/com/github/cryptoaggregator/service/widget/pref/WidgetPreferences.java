package com.github.cryptoaggregator.service.widget.pref;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pschoffer on 2018-03-19.
 */

public class WidgetPreferences {
    private final Map<String, Boolean> enabledCurrencies;

    public WidgetPreferences() {
        this.enabledCurrencies = new LinkedHashMap<>();
    }

    public void setEnabled(String key, Boolean value) {
        enabledCurrencies.put(key, value);
    }

    public List<String> getCurrencies() {
        final List<String> currencies = new ArrayList<>();

        for (Map.Entry<String, Boolean> currencyEntry : enabledCurrencies.entrySet()) {
            currencies.add(currencyEntry.getKey());
        }
        return currencies;
    }

    public List<String> getEnabledCurrencies() {
        final List<String> enabledCurrencies = new ArrayList<>();

        for (String currency : getCurrencies()) {
            if (isEnabled(currency)) {
                enabledCurrencies.add(currency);
            }
        }

        return enabledCurrencies;
    }

    public boolean isEnabled(String coin) {
        final Boolean isEnabled = enabledCurrencies.get(coin);
        return isEnabled == null ? false : isEnabled;
    }
}
