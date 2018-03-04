package com.github.cryptoaggregator.updator;

import android.content.Context;

import com.github.cryptoaggregator.listener.CoinInfo;

/**
 * Created by pschoffer on 2018-02-21.
 */

public interface Updator {
    void update(String symbol, CoinInfo state);

    Context getContext();
}
