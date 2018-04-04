package com.github.cryptoaggregator.updator;

import com.github.cryptoaggregator.listener.http.CoinInfo;

/**
 * Created by pschoffer on 2018-04-03.
 */

public interface BrowserUpdator {
    void update(CoinInfo[] coinInfos);

    void updateWithError();

    void updateWithLoading();
}
