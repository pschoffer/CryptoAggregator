package com.github.cryptoaggregator.listener.button;

import android.app.Activity;

import com.github.cryptoaggregator.service.widget.pref.WidgetPreferenceServiceFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by pschoffer on 2018-03-16.
 */
@Singleton
public class MoreConfigListenerFactory {

    @Inject
    public MoreConfigListenerFactory() {
    }

    public MoreConfigListener create(Activity configActivity) {
        return new MoreConfigListener(configActivity);
    }
}
