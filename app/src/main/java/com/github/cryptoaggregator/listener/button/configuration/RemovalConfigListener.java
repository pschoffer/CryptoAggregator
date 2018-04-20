package com.github.cryptoaggregator.listener.button.configuration;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.github.cryptoaggregator.MainWidget;
import com.github.cryptoaggregator.R;
import com.github.cryptoaggregator.service.pref.GlobalPreferences;
import com.github.cryptoaggregator.service.pref.PreferenceService;
import com.github.cryptoaggregator.service.pref.PreferenceServiceFactory;
import com.github.cryptoaggregator.service.pref.WidgetPreferences;
import com.github.cryptoaggregator.util.Logger;

/**
 * Created by pschoffer on 2018-03-15.
 */

public class RemovalConfigListener implements View.OnClickListener {
    private final Activity configActivity;
    private final PreferenceServiceFactory preferenceServiceFactory;
    private final String currencyToBeRemoved;

    RemovalConfigListener(PreferenceServiceFactory preferenceServiceFactory, Activity context, String currencyToBeRemoved) {
        this.preferenceServiceFactory = preferenceServiceFactory;
        this.configActivity = context;
        this.currencyToBeRemoved = currencyToBeRemoved;
    }

    @Override
    public void onClick(View view) {
        Logger.info("Processing removal of available currency " + currencyToBeRemoved);

        final PreferenceService preferenceService = preferenceServiceFactory.create(configActivity);
        final GlobalPreferences globalPreferences = preferenceService.loadGlobalPreferences();

        globalPreferences.removeCurrency(currencyToBeRemoved);

        preferenceService.persistGlobalPreferences(globalPreferences);

        configActivity.recreate();
    }
}
