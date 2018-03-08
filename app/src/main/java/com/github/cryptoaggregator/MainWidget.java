package com.github.cryptoaggregator;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.github.cryptoaggregator.dependency.DaggerMainComponent;
import com.github.cryptoaggregator.dependency.MainComponent;
import com.github.cryptoaggregator.dependency.MainModule;
import com.github.cryptoaggregator.dependency.MainModule_CoinServiceFactory;
import com.github.cryptoaggregator.service.android.WidgetRemoteViewsService;
import com.github.cryptoaggregator.service.coin.CoinMarketService;
import com.github.cryptoaggregator.service.coin.CoinService;
import com.github.cryptoaggregator.updator.MainWidgetUpdator;
import com.github.cryptoaggregator.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link MainWidgetConfigureActivity MainWidgetConfigureActivity}
 */
public class MainWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        final CoinService coinService = DaggerMainComponent.builder().build().getCoinService();
        List<String> coins = new ArrayList<>();
        coins.add("bitcoin");
        coins.add("ethereum");
        final WidgetRemoteViewsService widgetRemoteViewsService = new WidgetRemoteViewsService(context.getPackageName());
        final MainWidgetUpdator mainWidgetUpdator = new MainWidgetUpdator(context, appWidgetManager, appWidgetId, widgetRemoteViewsService, coins);

        coinService.triggerUpdate(coins, mainWidgetUpdator);

        setLoadingContent(appWidgetManager, appWidgetId, coins, widgetRemoteViewsService);
    }

    private static void setLoadingContent(AppWidgetManager appWidgetManager, int appWidgetId, List<String> coins, WidgetRemoteViewsService widgetRemoteViewsService) {
        Logger.info("Setting loading content");
        Map<String, String> loadingUpdate = new HashMap<>();
        for (String coin : coins) {
            loadingUpdate.put(coin, "loading...");
        }

        final RemoteViews loadingViews = widgetRemoteViewsService.createRemoteViews();
        widgetRemoteViewsService.setContent(loadingViews, loadingUpdate);
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

