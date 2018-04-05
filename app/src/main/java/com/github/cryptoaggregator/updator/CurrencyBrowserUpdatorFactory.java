package com.github.cryptoaggregator.updator;

import android.app.Activity;

import com.github.cryptoaggregator.listener.button.currbrowser.ClickCurrencyBrowserListenerFactory;
import com.github.cryptoaggregator.listener.button.currbrowser.SaveCurrencyBrowserListenerFactory;
import com.github.cryptoaggregator.service.pref.PreferenceServiceFactory;

import javax.inject.Inject;

/**
 * Created by pschoffer on 2018-04-03.
 */

public class CurrencyBrowserUpdatorFactory {
    private final SaveCurrencyBrowserListenerFactory saveCurrencyBrowserListenerFactory;
    private final ClickCurrencyBrowserListenerFactory clickCurrencyBrowserListenerFactory;
    private final PreferenceServiceFactory preferenceServiceFactory;

    @Inject
    public CurrencyBrowserUpdatorFactory(SaveCurrencyBrowserListenerFactory saveCurrencyBrowserListenerFactory, ClickCurrencyBrowserListenerFactory clickCurrencyBrowserListenerFactory, PreferenceServiceFactory preferenceServiceFactory) {
        this.saveCurrencyBrowserListenerFactory = saveCurrencyBrowserListenerFactory;
        this.clickCurrencyBrowserListenerFactory = clickCurrencyBrowserListenerFactory;
        this.preferenceServiceFactory = preferenceServiceFactory;
    }

    public CurrencyBrowserUpdator create(Activity currencyBrowser) {
        return new CurrencyBrowserUpdator(saveCurrencyBrowserListenerFactory, clickCurrencyBrowserListenerFactory, preferenceServiceFactory, currencyBrowser);
    }
}
