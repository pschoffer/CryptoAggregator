package com.github.cryptoaggregator.updator;

import android.content.Context;

/**
 * Created by pschoffer on 2018-02-21.
 */

public interface Updator {
    void update(String symbol, String state);

    Context getContext();
}
