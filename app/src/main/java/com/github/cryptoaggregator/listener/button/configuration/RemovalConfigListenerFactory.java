package com.github.cryptoaggregator.listener.button.configuration;

import android.app.Activity;

import com.github.cryptoaggregator.service.pref.PreferenceServiceFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by pschoffer on 2018-03-16.
 */
@Singleton
public class RemovalConfigListenerFactory {
    private final PreferenceServiceFactory preferenceServiceFactory;

    @Inject
    public RemovalConfigListenerFactory(PreferenceServiceFactory preferenceServiceFactory) {
        this.preferenceServiceFactory = preferenceServiceFactory;
    }

    public RemovalConfigListener create(Activity configActivity, String currencyToBeRemoved) {
        return new RemovalConfigListener(preferenceServiceFactory, configActivity, currencyToBeRemoved);
    }
}
