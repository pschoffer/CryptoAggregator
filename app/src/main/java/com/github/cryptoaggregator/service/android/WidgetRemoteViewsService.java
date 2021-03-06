package com.github.cryptoaggregator.service.android;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.github.cryptoaggregator.ConfigurationActivity;
import com.github.cryptoaggregator.R;
import com.github.cryptoaggregator.service.widget.WidgetService;
import com.github.cryptoaggregator.util.Logger;

import java.util.Map;

/**
 * Created by pschoffer on 2018-03-05.
 */

public class WidgetRemoteViewsService implements RemoteViewsService {
    private final Context context;
    private final IntentFactory intentFactory;

    public WidgetRemoteViewsService(Context context, IntentFactory intentFactory) {
        this.context = context;
        this.intentFactory = intentFactory;
    }

    @Override
    public RemoteViews createRemoteViews() {
        return new RemoteViews(context.getPackageName(), R.layout.main_widget);
    }

    @Override
    public void setContent(RemoteViews target, Map<String, String> content) {
        Logger.info("Setting up CONTENT for the widget.");
        StringBuilder keys = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (Map.Entry<String, String> keyValue : content.entrySet()) {
            if (!keys.toString().isEmpty()) {
                keys.append("\n");
                values.append("\n");
            }
            keys.append(keyValue.getKey());
            values.append(keyValue.getValue());
        }
        target.setTextViewText(R.id.text_symbol, keys.toString());
        target.setTextViewText(R.id.text_value, values.toString());
    }

    @Override
    public void setIntents(RemoteViews target, int appWidgetId) {
        Logger.info("Setting up INTENTS for the widget.");
        Intent configIntent = intentFactory.createIntent(context, ConfigurationActivity.class);
        configIntent.setAction("Config");
        configIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        target.setOnClickPendingIntent(R.id.text_symbol, configPendingIntent);

        final Intent updateIntent = intentFactory.createIntent(context, WidgetService.class);
        updateIntent.setAction("Update");
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        final PendingIntent updatePendingIntent = PendingIntent.getService(context, 0, updateIntent, 0);
        target.setOnClickPendingIntent(R.id.text_value, updatePendingIntent);
    }
}
