package com.github.cryptoaggregator.service.coin;

import com.github.cryptoaggregator.updator.WidgetUpdator;

import java.util.List;

/**
 * Created by pschoffer on 2018-02-21.
 */

public interface CoinService {
    void triggerUpdate(List<String> symbols, WidgetUpdator widgetUpdator);
}
