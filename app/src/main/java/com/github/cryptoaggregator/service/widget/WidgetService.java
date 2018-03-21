package com.github.cryptoaggregator.service.widget;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.github.cryptoaggregator.util.Logger;

/**
 * Created by pschoffer on 2018-03-20.
 */

public class WidgetService extends IntentService {


    public static final String SERVICE_NAME = "WidgetService";

    public WidgetService() {
        super(SERVICE_NAME);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Logger.info("Handling intent from widget.");
    }
}
