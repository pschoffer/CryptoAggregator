package com.github.cryptoaggregator.updator;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.github.cryptoaggregator.R;

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
    private final Map<String, String> results;
    private final List<String> currencySymbols;

    public MainWidgetUpdator(Context context, AppWidgetManager appWidgetManager, int appWidgetId, List<String> currencySymbols) {
        this.context = context;
        this.appWidgetManager = appWidgetManager;
        this.appWidgetId = appWidgetId;

        this.currencySymbols = currencySymbols;
        this.results = new HashMap<>();
    }

    @Override
    public void update(String symbol, String state) {

        results.put(symbol, state);
        if (results.size() == currencySymbols.size()) {


            RemoteViews views = configureRemoteViews();
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @NonNull
    private RemoteViews configureRemoteViews() {
        StringBuilder symbols = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (String currencySymbol : currencySymbols) {
            if (!symbols.toString().isEmpty()) {
                symbols.append("\n");
                values.append("\n");
            }
            symbols.append(currencySymbol.toUpperCase());
            values.append(results.get(currencySymbol));
        }


        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main_widget);
        remoteViews.setTextViewText(R.id.text_symbol, symbols);
        remoteViews.setTextViewText(R.id.text_value, values);

        return remoteViews;
    }

    @Override
    public Context getContext() {
        return context;
    }
}
