package com.github.cryptoaggregator.listener.button.currbrowser;

import android.app.Activity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by pschoffer on 2018-03-16.
 */
@Singleton
public class SaveCurrencyBrowserListenerFactory {

    @Inject
    public SaveCurrencyBrowserListenerFactory() {
    }

    public SaveCurrencyBrowserListener create(Activity activity) {
        return new SaveCurrencyBrowserListener(activity);
    }
}
