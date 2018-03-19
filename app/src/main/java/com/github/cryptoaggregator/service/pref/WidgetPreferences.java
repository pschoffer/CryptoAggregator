package com.github.cryptoaggregator.service.pref;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pschoffer on 2018-03-19.
 */

public class WidgetPreferences {
    private final Map<String, Boolean> enabledCurrencis;

    public WidgetPreferences() {
        this.enabledCurrencis = new HashMap<>();
    }

    public void setEnabled(String key, Boolean value) {
        enabledCurrencis.put(key, value);
    }
}
