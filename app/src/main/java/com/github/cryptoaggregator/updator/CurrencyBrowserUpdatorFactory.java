package com.github.cryptoaggregator.updator;

import android.app.Activity;

import com.github.cryptoaggregator.listener.button.currbrowser.SaveCurrencyBrowserListenerFactory;

import javax.inject.Inject;

/**
 * Created by pschoffer on 2018-04-03.
 */

public class CurrencyBrowserUpdatorFactory {
    private final SaveCurrencyBrowserListenerFactory saveCurrencyBrowserListenerFactory;

    @Inject
    public CurrencyBrowserUpdatorFactory(SaveCurrencyBrowserListenerFactory saveCurrencyBrowserListenerFactory) {
        this.saveCurrencyBrowserListenerFactory = saveCurrencyBrowserListenerFactory;
    }

    public CurrencyBrowserUpdator create(Activity currencyBrowser) {
        return new CurrencyBrowserUpdator(saveCurrencyBrowserListenerFactory, currencyBrowser);
    }
}
