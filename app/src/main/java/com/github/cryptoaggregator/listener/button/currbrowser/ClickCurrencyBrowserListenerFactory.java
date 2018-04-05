package com.github.cryptoaggregator.listener.button.currbrowser;

import android.app.Activity;

import com.github.cryptoaggregator.service.pref.PreferenceServiceFactory;

import javax.inject.Inject;

/**
 * Created by pschoffer on 2018-04-05.
 */

public class ClickCurrencyBrowserListenerFactory {
    private final PreferenceServiceFactory preferenceServiceFactory;

    @Inject
    public ClickCurrencyBrowserListenerFactory(PreferenceServiceFactory preferenceServiceFactory) {
        this.preferenceServiceFactory = preferenceServiceFactory;
    }

    public ClickCurrencyBrowserListener create(String currency, Activity currencyBrowser) {
        return new ClickCurrencyBrowserListener(preferenceServiceFactory, currency, currencyBrowser);
    }
}
