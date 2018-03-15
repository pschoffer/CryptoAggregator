package com.github.cryptoaggregator.updator;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.github.cryptoaggregator.listener.CoinInfo;
import com.github.cryptoaggregator.service.android.RemoteViewsService;
import com.github.cryptoaggregator.service.android.WidgetRemoteViewsServiceFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by pschoffer on 2018-02-21.
 */

public class MainWidgetUpdator implements Updator {
    private final Context context;
    private final int appWidgetId;
    private final RemoteViewsService remoteViewsService;
    private final Map<String, CoinInfo> results;
    private final List<String> coins;

    public MainWidgetUpdator(Context context, int appWidgetId, List<String> coins, WidgetRemoteViewsServiceFactory widgetRemoteViewsServiceFactory) {
        this.context = context;
        this.appWidgetId = appWidgetId;
        this.remoteViewsService = widgetRemoteViewsServiceFactory.create(context);

        this.coins = coins;
        this.results = new HashMap<>();
    }

    @Override
    public void update(String symbol, CoinInfo state) {
        results.put(symbol, state);
        if (results.size() == coins.size()) {
            RemoteViews views = configureRemoteViews();
            final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @NonNull
    private RemoteViews configureRemoteViews() {
        Map<String, String> update = new HashMap<>();
        for (String coin : coins) {
            final CoinInfo state = results.get(coin);
            final String symbol = state.getSymbol().toUpperCase(Locale.getDefault());
            final String value = "$" + state.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP);
            update.put(symbol, value);
        }

        final RemoteViews remoteViews = remoteViewsService.createRemoteViews();
        remoteViewsService.setContent(remoteViews, update);
        remoteViewsService.setIntents(remoteViews, appWidgetId);


        return remoteViews;
    }

    @Override
    public Context getContext() {
        return context;
    }
}
