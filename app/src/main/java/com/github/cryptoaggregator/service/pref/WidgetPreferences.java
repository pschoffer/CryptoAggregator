package com.github.cryptoaggregator.service.pref;

import java.util.LinkedHashMap;
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

    public Map<String, Boolean> getEnabledCurrencies() {
        return enabledCurrencies;
    }
}
