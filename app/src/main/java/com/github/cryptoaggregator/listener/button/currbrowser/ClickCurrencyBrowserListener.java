package com.github.cryptoaggregator.listener.button.currbrowser;

import android.app.Activity;
import android.view.View;

import com.github.cryptoaggregator.service.pref.GlobalPreferences;
import com.github.cryptoaggregator.service.pref.PreferenceService;
import com.github.cryptoaggregator.service.pref.PreferenceServiceFactory;

/**
 * Created by pschoffer on 2018-04-05.
 */

public class ClickCurrencyBrowserListener implements View.OnClickListener {
    private final PreferenceServiceFactory preferenceServiceFactory;
    private final String currency;
    private final Activity currencyBrowser;

    public ClickCurrencyBrowserListener(PreferenceServiceFactory preferenceServiceFactory, String currency, Activity currencyBrowser) {
        this.preferenceServiceFactory = preferenceServiceFactory;
        this.currency = currency;
        this.currencyBrowser = currencyBrowser;
    }

    @Override
    public void onClick(View view) {
        final PreferenceService preferenceService = preferenceServiceFactory.create(currencyBrowser);
        final GlobalPreferences globalPreferences = preferenceService.loadGlobalPreferences();
        globalPreferences.addCurrency(currency);
        preferenceService.persistGlobalPreferences(globalPreferences);

        currencyBrowser.finish();
    }
}
