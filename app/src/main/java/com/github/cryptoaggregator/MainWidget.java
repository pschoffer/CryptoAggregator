package com.github.cryptoaggregator;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.github.cryptoaggregator.dependency.DaggerMainComponent;
import com.github.cryptoaggregator.dependency.MainComponent;
import com.github.cryptoaggregator.service.android.RemoteViewsService;
import com.github.cryptoaggregator.service.android.WidgetRemoteViewsServiceFactory;
import com.github.cryptoaggregator.service.coin.CoinService;
import com.github.cryptoaggregator.service.pref.PreferenceService;
import com.github.cryptoaggregator.service.pref.PreferenceServiceFactory;
import com.github.cryptoaggregator.service.pref.WidgetPreferences;
import com.github.cryptoaggregator.updator.MainWidgetUpdatorFactory;
import com.github.cryptoaggregator.updator.WidgetUpdator;
import com.github.cryptoaggregator.util.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link ConfigurationActivity ConfigurationActivity}
 */
public class MainWidget extends AppWidgetProvider {

    private static CoinService coinService;
    private static WidgetRemoteViewsServiceFactory widgetRemoteViewsServiceFactory;
    private static MainWidgetUpdatorFactory mainWidgetUpdatorFactory;
    private static PreferenceServiceFactory preferenceServiceFactory;

    static public void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        initInjection();

        final PreferenceService preferenceService = preferenceServiceFactory.create(context);
        final WidgetPreferences preferences = preferenceService.loadWidgetPreferences(appWidgetId);
        List<String> coins = preferences.getEnabledCurrencies();
        if (coins.isEmpty()) {
            coins = preferences.getCurrencies();
        }
        final WidgetUpdator mainWidgetUpdator = mainWidgetUpdatorFactory.create(context, appWidgetId, coins);

        coinService.triggerUpdate(coins, mainWidgetUpdator);

        setLoadingContent(appWidgetManager, appWidgetId, coins, widgetRemoteViewsServiceFactory.create(context));
    }

    private static void initInjection() {
        final MainComponent component = DaggerMainComponent.builder().build();
        coinService = component.getCoinService();
        widgetRemoteViewsServiceFactory = component.getWidgetRemoteViewsServiceFactory();
        mainWidgetUpdatorFactory = component.getMainWidgetUpdatorFactory();
        preferenceServiceFactory = component.getWidgetPreferenceServiceFactory();
    }

    private static void setLoadingContent(AppWidgetManager appWidgetManager, int appWidgetId, List<String> coins, RemoteViewsService remoteViewsService) {
        Logger.info("Setting loading content");
        Map<String, String> loadingUpdate = new HashMap<>();
        for (String coin : coins) {
            loadingUpdate.put(coin, "loading...");
        }

        final RemoteViews loadingViews = remoteViewsService.createRemoteViews();
        remoteViewsService.setContent(loadingViews, loadingUpdate);
        remoteViewsService.setIntents(loadingViews, appWidgetId);
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
        final PreferenceService preferenceService = preferenceServiceFactory.create(context);
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            preferenceService.clearWidgetPreferences(appWidgetId);
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

