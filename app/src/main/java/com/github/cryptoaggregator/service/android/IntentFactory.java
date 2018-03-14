package com.github.cryptoaggregator.service.android;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by pschoffer on 2018-03-14.
 */
@Singleton
public class IntentFactory {

    @Inject
    public IntentFactory() {}
    public Intent createIntent(Context context, Class<?> klass) {
        return new Intent(context, klass);
    }
}
