package com.github.cryptoaggregator.updator;

import android.app.Activity;

import com.github.cryptoaggregator.R;
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

    @Override
    public void updateWithError() {
        Logger.info("Updating currency browser with Error.");

        currencyBrowser.setContentView(R.layout.currency_browser);

    }
}
