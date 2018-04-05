package com.github.cryptoaggregator.listener.button.configuration;

import android.app.Activity;

import com.github.cryptoaggregator.service.pref.WidgetPreferenceServiceFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by pschoffer on 2018-03-16.
 */
@Singleton
public class SaveConfigListenerFactory {
    private final WidgetPreferenceServiceFactory widgetPreferenceServiceFactory;

    @Inject
    public SaveConfigListenerFactory(WidgetPreferenceServiceFactory widgetPreferenceServiceFactory) {
        this.widgetPreferenceServiceFactory = widgetPreferenceServiceFactory;
    }

    public SaveConfigListener create(Activity configActivity, int appWidgetId) {
        return new SaveConfigListener(widgetPreferenceServiceFactory, configActivity, appWidgetId);
    }
}
