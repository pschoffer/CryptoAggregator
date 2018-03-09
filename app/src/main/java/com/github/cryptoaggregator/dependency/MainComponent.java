package com.github.cryptoaggregator.dependency;

import com.github.cryptoaggregator.service.android.WidgetRemoteViewsServiceFactory;
import com.github.cryptoaggregator.service.coin.CoinService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pschoffer on 2018-03-08.
 */

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    CoinService getCoinService();

    WidgetRemoteViewsServiceFactory getWidgetRemoteViewsServiceFactory();
}
