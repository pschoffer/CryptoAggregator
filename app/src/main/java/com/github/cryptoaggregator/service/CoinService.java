package com.github.cryptoaggregator.service;

import com.github.cryptoaggregator.updator.Updator;

/**
 * Created by pschoffer on 2018-02-21.
 */

public interface CoinService {
    void triggerUpdate(Updator updator);
}