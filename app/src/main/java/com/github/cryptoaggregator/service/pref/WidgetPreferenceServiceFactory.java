package com.github.cryptoaggregator.service.pref;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by pschoffer on 2018-03-16.
 */

@Singleton
public class WidgetPreferenceServiceFactory {
    @Inject
    public WidgetPreferenceServiceFactory() {}

    public WidgetPreferenceService create(Context context) {
        return new WidgetPreferenceService(context);
    }
}
