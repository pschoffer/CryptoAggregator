package com.github.cryptoaggregator.updator;

import android.content.Context;

import com.github.cryptoaggregator.listener.http.CoinInfo;

/**
 * Created by pschoffer on 2018-02-21.
 */

public interface WidgetUpdator {
    void update(String symbol, CoinInfo state);

    Context getContext();
}
