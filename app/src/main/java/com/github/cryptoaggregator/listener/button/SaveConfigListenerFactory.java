package com.github.cryptoaggregator.listener.button;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by pschoffer on 2018-03-16.
 */
@Singleton
public class SaveConfigListenerFactory {
    @Inject
    public SaveConfigListenerFactory() {}

    public SaveConfigListener create(Context context, int appWidgetId) {
        return new SaveConfigListener(context, appWidgetId);
    }
}
