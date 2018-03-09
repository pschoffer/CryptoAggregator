package com.github.cryptoaggregator.updator;

import android.content.Context;

import com.github.cryptoaggregator.service.android.WidgetRemoteViewsServiceFactory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by pschoffer on 2018-03-09.
 */
@Singleton
public class MainWidgetUpdatorFactory {
    private final WidgetRemoteViewsServiceFactory widgetRemoteViewsServiceFactory;

    @Inject
    public MainWidgetUpdatorFactory(WidgetRemoteViewsServiceFactory widgetRemoteViewsServiceFactory) {
        this.widgetRemoteViewsServiceFactory = widgetRemoteViewsServiceFactory;
    }

    public Updator create(Context context, int appWidgetId, List<String> coins) {
        return new MainWidgetUpdator(context, appWidgetId, coins, widgetRemoteViewsServiceFactory);
    }
}
