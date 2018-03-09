package com.github.cryptoaggregator.service.android;

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

    public RemoteViewsService create(String packageName) {
        return new WidgetRemoteViewsService(packageName);
    }

}
