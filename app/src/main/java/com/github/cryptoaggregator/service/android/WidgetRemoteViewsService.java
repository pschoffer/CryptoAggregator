package com.github.cryptoaggregator.service.android;

import android.widget.RemoteViews;

/**
 * Created by pschoffer on 2018-03-05.
 */

public class WidgetRemoteViewsService {
    public RemoteViews createInstance(String packageName, int layoutId) {
        return new RemoteViews(packageName, layoutId);
    }
}
