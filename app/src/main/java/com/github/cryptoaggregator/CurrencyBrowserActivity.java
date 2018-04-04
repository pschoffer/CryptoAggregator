package com.github.cryptoaggregator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.cryptoaggregator.dependency.CurrencyBrowserComponent;
import com.github.cryptoaggregator.dependency.DaggerCurrencyBrowserComponent;
import com.github.cryptoaggregator.listener.button.currbrowser.SaveCurrencyBrowserListenerFactory;
import com.github.cryptoaggregator.service.coin.CoinService;
import com.github.cryptoaggregator.updator.BrowserUpdator;
import com.github.cryptoaggregator.updator.CurrencyBrowserUpdator;
import com.github.cryptoaggregator.updator.CurrencyBrowserUpdatorFactory;
import com.github.cryptoaggregator.util.Logger;

import javax.inject.Inject;

/**
 * The configuration screen for the {@link MainWidget MainWidget} AppWidget.
 */
public class CurrencyBrowserActivity extends Activity {
    @Inject
    public SaveCurrencyBrowserListenerFactory saveCurrencyBrowserListenerFactory;
    @Inject
    public CoinService coinService;
    @Inject
    public CurrencyBrowserUpdatorFactory currencyBrowserUpdatorFactory;

    public CurrencyBrowserActivity() {
        final CurrencyBrowserComponent component = DaggerCurrencyBrowserComponent.builder().build();
        component.inject(this);
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Logger.info("Creating Currency Browser activity.");

        final BrowserUpdator currencyBrowserUpdator = currencyBrowserUpdatorFactory.create(this);

        currencyBrowserUpdator.updateWithLoading();

        coinService.triggerListLoading(this, currencyBrowserUpdator);
    }



}

