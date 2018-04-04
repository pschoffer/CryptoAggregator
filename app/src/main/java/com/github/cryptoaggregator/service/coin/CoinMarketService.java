package com.github.cryptoaggregator.service.coin;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.cryptoaggregator.listener.http.CoinMarketListListener;
import com.github.cryptoaggregator.listener.http.CoinMarketWidgetListener;
import com.github.cryptoaggregator.updator.BrowserUpdator;
import com.github.cryptoaggregator.updator.WidgetUpdator;
import com.github.cryptoaggregator.util.Logger;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by pschoffer on 2018-02-21.
 */

public class CoinMarketService implements CoinService {
    private final static String URL = "https://api.coinmarketcap.com/v1/ticker/";

    @Inject
    public CoinMarketService() {
    }

    @Override
    public void triggerUpdate(List<String> coins, WidgetUpdator widgetUpdator) {
        Logger.info("Triggered update for " + coins.toString());


        RequestQueue queue = Volley.newRequestQueue(widgetUpdator.getContext());

        for (String coin : coins) {
            CoinMarketWidgetListener listener = new CoinMarketWidgetListener(coin, widgetUpdator);
            StringRequest request = new StringRequest(URL + coin, listener, listener);
            queue.add(request);
        }
    }

    @Override
    public void triggerListLoading(Context context, BrowserUpdator currencyBrowserUpdator) {
        Logger.info("Triggered loading of the list of currencies.");

        RequestQueue queue = Volley.newRequestQueue(context);
        CoinMarketListListener listener = new CoinMarketListListener(currencyBrowserUpdator);
        StringRequest request = new StringRequest(URL, listener, listener);

        queue.add(request);
    }
}
