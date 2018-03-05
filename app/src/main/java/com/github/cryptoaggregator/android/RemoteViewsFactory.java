package com.github.cryptoaggregator.android;

import android.widget.RemoteViews;

/**
 * Created by pschoffer on 2018-03-05.
 */

public class RemoteViewsFactory {
    public RemoteViews createInstance(String packageName, int layoutId) {
        return new RemoteViews(packageName, layoutId);
    }
}
