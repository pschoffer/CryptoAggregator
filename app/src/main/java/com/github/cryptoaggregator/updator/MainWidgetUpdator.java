package com.github.cryptoaggregator.updator;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.widget.RemoteViews;

import com.github.cryptoaggregator.R;

/**
 * Created by pschoffer on 2018-02-21.
 */

public class MainWidgetUpdator implements Updator {
    private final Context context;
    private final AppWidgetManager appWidgetManager;
    private final int appWidgetId;

    public MainWidgetUpdator(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        this.context = context;
        this.appWidgetManager = appWidgetManager;
        this.appWidgetId = appWidgetId;
    }

    @Override
    public void update(String state) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main_widget);
        views.setTextViewText(R.id.appwidget_text, state);


        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public Context getContext() {
        return context;
    }
}
