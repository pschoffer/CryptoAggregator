package com.github.cryptoaggregator;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.github.cryptoaggregator.android.RemoteViewsFactory;
import com.github.cryptoaggregator.service.CoinMarketService;
import com.github.cryptoaggregator.updator.MainWidgetUpdator;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link MainWidgetConfigureActivity MainWidgetConfigureActivity}
 */
public class MainWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        final CoinMarketService coinMarketService = new CoinMarketService();
        List<String> coins = new ArrayList<>();
        coins.add("bitcoin");
        coins.add("ethereum");
        final RemoteViewsFactory remoteViewsFactory = new RemoteViewsFactory();
        final MainWidgetUpdator mainWidgetUpdator = new MainWidgetUpdator(context, appWidgetManager, appWidgetId, remoteViewsFactory, coins);

        coinMarketService.triggerUpdate(coins, mainWidgetUpdator);

        StringBuilder symbols = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (String coin : coins) {
            if (!symbols.toString().isEmpty()) {
                symbols.append("\n");
                values.append("\n");
            }
            symbols.append(coin);
            values.append("loading...");
        }

        final RemoteViews loadingViews = remoteViewsFactory.createInstance(context.getPackageName(), R.layout.main_widget);
        loadingViews.setTextViewText(R.id.text_symbol, symbols.toString());
        loadingViews.setTextViewText(R.id.text_value, values.toString());
        appWidgetManager.updateAppWidget(appWidgetId, loadingViews);
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

