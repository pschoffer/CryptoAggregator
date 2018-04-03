package com.github.cryptoaggregator.updator;

import android.app.Activity;

import javax.inject.Inject;

/**
 * Created by pschoffer on 2018-04-03.
 */

public class CurrencyBrowserUpdatorFactory {
    @Inject
    public CurrencyBrowserUpdatorFactory() {}

    public CurrencyBrowserUpdator create(Activity currencyBrowser) {
        return new CurrencyBrowserUpdator(currencyBrowser);
    }
}
