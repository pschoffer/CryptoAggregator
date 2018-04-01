package com.github.cryptoaggregator.dependency;

import com.github.cryptoaggregator.CurrencyBrowserActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pschoffer on 2018-04-01.
 */

@Singleton
@Component(modules = MainModule.class)
public interface CurrencyBrowserComponent {
    void inject(CurrencyBrowserActivity currencyBrowserActivity);
}
