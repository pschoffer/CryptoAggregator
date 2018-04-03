package com.github.cryptoaggregator.updator;

import android.app.Activity;

import com.github.cryptoaggregator.util.Logger;

/**
 * Created by pschoffer on 2018-04-03.
 */

public class CurrencyBrowserUpdator implements BrowserUpdator {
    private final Activity currencyBrowser;

    public CurrencyBrowserUpdator(Activity currencyBrowser) {
        this.currencyBrowser = currencyBrowser;
    }

    @Override
    public void update() {
        Logger.info("Updating currency browser.");

    }
}
