package com.github.cryptoaggregator.listener.button.configuration;

import android.app.Activity;

import com.github.cryptoaggregator.service.pref.PreferenceServiceFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by pschoffer on 2018-03-16.
 */
@Singleton
public class SaveConfigListenerFactory {
    private final PreferenceServiceFactory preferenceServiceFactory;

    @Inject
    public SaveConfigListenerFactory(PreferenceServiceFactory preferenceServiceFactory) {
        this.preferenceServiceFactory = preferenceServiceFactory;
    }

    public SaveConfigListener create(Activity configActivity, int appWidgetId) {
        return new SaveConfigListener(preferenceServiceFactory, configActivity, appWidgetId);
    }
}
