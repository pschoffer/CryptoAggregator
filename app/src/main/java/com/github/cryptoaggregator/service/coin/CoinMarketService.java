package com.github.cryptoaggregator.service.coin;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.cryptoaggregator.listener.CoinMarketListener;
import com.github.cryptoaggregator.updator.Updator;
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
    public void triggerUpdate(List<String> coins, Updator updator) {
        Logger.info("Triggered update for " + coins.toString());


        RequestQueue queue = Volley.newRequestQueue(updator.getContext());

        for (String coin : coins) {
            Response.Listener<String> listener = new CoinMarketListener(coin, updator);
            StringRequest request = new StringRequest(URL + coin, listener, null);
            queue.add(request);
        }
    }
}
