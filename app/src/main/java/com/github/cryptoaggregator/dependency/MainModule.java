package com.github.cryptoaggregator.dependency;

import com.github.cryptoaggregator.service.coin.CoinMarketService;
import com.github.cryptoaggregator.service.coin.CoinService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pschoffer on 2018-03-08.
 */
@Module
public class MainModule {
    @Provides
    public CoinService coinService(CoinMarketService coinMarketService) {
        return coinMarketService;
    }
}
