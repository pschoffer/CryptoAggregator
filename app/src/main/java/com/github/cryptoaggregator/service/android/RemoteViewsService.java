package com.github.cryptoaggregator.service.android;

import android.widget.RemoteViews;

import java.util.Map;

/**
 * Created by pschoffer on 2018-03-06.
 */

public interface RemoteViewsService {
    RemoteViews createRemoteViews();

    void setContent(RemoteViews target, Map<String, String> values);

    void setIntents(RemoteViews remoteViews);
}
