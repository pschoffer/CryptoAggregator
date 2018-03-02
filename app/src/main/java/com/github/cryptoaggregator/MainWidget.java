package com.github.cryptoaggregator;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.TableLayout;
import android.widget.TextView;

import com.github.cryptoaggregator.service.CoinMarketService;
import com.github.cryptoaggregator.updator.MainWidgetUpdator;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link MainWidgetConfigureActivity MainWidgetConfigureActivity}
 */
public class MainWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        final CoinMarketService coinMarketService = new CoinMarketService();
        final MainWidgetUpdator mainWidgetUpdator = new MainWidgetUpdator(context, appWidgetManager, appWidgetId);

        coinMarketService.triggerUpdate(mainWidgetUpdator);

        final MainWidgetUpdator tempWidgetUpdator = new MainWidgetUpdator(context, appWidgetManager, appWidgetId);
        tempWidgetUpdator.update("Loading ...");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            MainWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

