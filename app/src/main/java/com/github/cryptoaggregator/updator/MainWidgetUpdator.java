package com.github.cryptoaggregator.updator;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.github.cryptoaggregator.listener.CoinInfo;
import com.github.cryptoaggregator.service.android.WidgetRemoteViewsService;

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
    private final AppWidgetManager appWidgetManager;
    private final int appWidgetId;
    private final WidgetRemoteViewsService widgetRemoteViewsService;
    private final Map<String, CoinInfo> results;
    private final List<String> coins;

    public MainWidgetUpdator(Context context, AppWidgetManager appWidgetManager, int appWidgetId, WidgetRemoteViewsService widgetRemoteViewsService, List<String> coins) {
        this.context = context;
        this.appWidgetManager = appWidgetManager;
        this.appWidgetId = appWidgetId;
        this.widgetRemoteViewsService = widgetRemoteViewsService;

        this.coins = coins;
        this.results = new HashMap<>();
    }

    @Override
    public void update(String symbol, CoinInfo state) {
        results.put(symbol, state);
        if (results.size() == coins.size()) {
            RemoteViews views = configureRemoteViews();
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

        final RemoteViews remoteViews = widgetRemoteViewsService.createRemoteViews();
        widgetRemoteViewsService.setContent(remoteViews, update);

        return remoteViews;
    }

    @Override
    public Context getContext() {
        return context;
    }
}
