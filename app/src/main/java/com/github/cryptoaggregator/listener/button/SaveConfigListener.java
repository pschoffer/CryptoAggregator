package com.github.cryptoaggregator.listener.button;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.github.cryptoaggregator.MainWidget;
import com.github.cryptoaggregator.R;
import com.github.cryptoaggregator.service.pref.WidgetPreferenceService;
import com.github.cryptoaggregator.service.pref.WidgetPreferenceServiceFactory;
import com.github.cryptoaggregator.service.pref.WidgetPreferences;
import com.github.cryptoaggregator.util.Logger;

/**
 * Created by pschoffer on 2018-03-15.
 */

public class SaveConfigListener implements View.OnClickListener {
    private final Activity configActivity;
    private final int appWidgetId;
    private final WidgetPreferenceServiceFactory widgetPreferenceServiceFactory;

    SaveConfigListener(WidgetPreferenceServiceFactory widgetPreferenceServiceFactory, Activity context, int appWidgetId) {
        this.widgetPreferenceServiceFactory = widgetPreferenceServiceFactory;
        this.configActivity = context;
        this.appWidgetId = appWidgetId;
    }

    @Override
    public void onClick(View view) {
        Logger.info("Processing submitted configuration for widget " + appWidgetId);

        final WidgetPreferences preferences = new WidgetPreferences();

        final ViewGroup layoutView = configActivity.findViewById(R.id.configureLayout);
        for (int i = 0; i < layoutView.getChildCount(); i++) {
            final ViewGroup row = (ViewGroup) layoutView.getChildAt(i);
            final View checkbox = row.getChildAt(0);
            // TODO replace with tag or something
            if (checkbox instanceof CheckBox) {
                // TODO replace with tag or something
                final TextView label = (TextView) row.getChildAt(1);
                final String coin = String.valueOf(label.getText());
                final Boolean checked = ((CheckBox) checkbox).isChecked();

                preferences.setEnabled(coin, checked);
            }
        }

        final WidgetPreferenceService widgetPreferenceService = widgetPreferenceServiceFactory.create();
        widgetPreferenceService.persistPreferences(appWidgetId, preferences);

        // It is the responsibility of the configuration activity to update the app widget
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(configActivity);
        MainWidget.updateAppWidget(configActivity, appWidgetManager, appWidgetId);

        configActivity.finish();
    }
}
