package com.github.cryptoaggregator.util;

import android.util.Log;

/**
 * Created by pschoffer on 2018-02-21.
 */

public class Logger {
    private final static String TAG = "CryptoAggregator";

    public static void info(String msg) {
        Log.i(TAG, msg);
    }
    public static void error(String msg) {
        Log.e(TAG, msg);
    }
    public static void error(String msg, Throwable tr) {
        Log.e(TAG, msg, tr);
    }
}
