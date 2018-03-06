package com.github.cryptoaggregator.updator;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.github.cryptoaggregator.R;
import com.github.cryptoaggregator.service.android.RemoteViewsFactory;
import com.github.cryptoaggregator.listener.CoinInfo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pschoffer on 2018-02-21.
 */

public class MainWidgetUpdator implements Updator {
    private final Context context;
    private final AppWidgetManager appWidgetManager;
    private final int appWidgetId;
    private final RemoteViewsFactory remoteViewsFactory;
    private final Map<String, CoinInfo> results;
    private final List<String> coins;

    public MainWidgetUpdator(Context context, AppWidgetManager appWidgetManager, int appWidgetId, RemoteViewsFactory remoteViewsFactory, List<String> coins) {
        this.context = context;
        this.appWidgetManager = appWidgetManager;
        this.appWidgetId = appWidgetId;
        this.remoteViewsFactory = remoteViewsFactory;

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
        StringBuilder symbols = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (String coin : coins) {
            final CoinInfo state = results.get(coin);
            if (!symbols.toString().isEmpty()) {
                symbols.append("\n");
                values.append("\n");
            }
            symbols.append(state.getSymbol().toUpperCase());

            values.append("$");
            values.append(state.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP));
        }

        final RemoteViews remoteViews = remoteViewsFactory.createInstance(context.getPackageName(), R.layout.main_widget);
        remoteViews.setTextViewText(R.id.text_symbol, symbols);
        remoteViews.setTextViewText(R.id.text_value, values);

        return remoteViews;
    }

    @Override
    public Context getContext() {
        return context;
    }
}
