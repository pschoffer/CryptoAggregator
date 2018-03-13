package com.github.cryptoaggregator.service.android;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by pschoffer on 2018-03-09.
 */

@Singleton
public class WidgetRemoteViewsServiceFactory {
    @Inject
    public WidgetRemoteViewsServiceFactory() {

    }

    public RemoteViewsService create(Context context) {
        return new WidgetRemoteViewsService(context);
    }

}
