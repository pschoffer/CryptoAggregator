package com.github.cryptoaggregator.service.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.github.cryptoaggregator.util.Logger;

/**
 * Created by pschoffer on 2018-03-16.
 */

public class PreferenceService {
    private static final String PREFS_NAME = "com.github.cryptoaggregator.MainWidget";
    private static final String ENABLE_PREF_PREFIX = "ENABLE_COIN_";
    private static final String COINS_ORDER = "COINS_ORDER_";
    private static final String DEFAULT_COINS = "bitcoin|ethereum";
    private static final String COINS_SEPARATOR = "|";

    private final Context context;

    PreferenceService(Context context) {
        this.context = context;
    }

    public void persistWidgetPreferences(int appWidgetId, WidgetPreferences preferences) {
        Logger.info("Persisting preferences for widget " + appWidgetId);
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();

        final StringBuilder coins = new StringBuilder();
        for (String coin : preferences.getCurrencies()) {
            final boolean enabled = preferences.isEnabled(coin);
            prefs.putBoolean(getCoinEnabledKey(appWidgetId, coin), enabled);

            if (!coins.toString().isEmpty()) {
                coins.append(COINS_SEPARATOR);
            }
            coins.append(coin);
        }
        prefs.putString(getCoinOrderKey(appWidgetId), coins.toString());
        prefs.apply();
    }

    public WidgetPreferences loadWidgetPreferences(int appWidgetId) {
        Logger.info("Loading preferences for widget " + appWidgetId);
        final WidgetPreferences widgetPreferences = new WidgetPreferences();

        final SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        final String coins = prefs.getString(getCoinOrderKey(appWidgetId), DEFAULT_COINS);
        for (String coin : coins.split("\\|")) {
            final boolean enabled = prefs.getBoolean(getCoinEnabledKey(appWidgetId, coin), false);
            widgetPreferences.setEnabled(coin, enabled);
        }

        return widgetPreferences;
    }

    public void clearWidgetPreferences(int appWidgetId) {
        Logger.info("Clearing preferences for widget " + appWidgetId);

        final SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        final String coins = prefs.getString(getCoinOrderKey(appWidgetId), "");

        final SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.remove(getCoinOrderKey(appWidgetId));

        for (String coin : coins.split("\\|")) {
            prefsEditor.remove(getCoinEnabledKey(appWidgetId, coin));
        }
        prefsEditor.apply();
    }

    @NonNull
    private String getCoinOrderKey(int appWidgetId) {
        return COINS_ORDER + appWidgetId;
    }

    private String getCoinEnabledKey(int appWidgetId, String coin) {
        return ENABLE_PREF_PREFIX + appWidgetId + "_" + coin;
    }
}
