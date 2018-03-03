package com.github.cryptoaggregator.service;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.cryptoaggregator.listener.CoinMarketListener;
import com.github.cryptoaggregator.updator.Updator;

import java.util.List;

/**
 * Created by pschoffer on 2018-02-21.
 */

public class CoinMarketService implements CoinService {
    private final static String URL = "https://api.coinmarketcap.com/v1/ticker/bitcoin/";

    @Override
    public void triggerUpdate(List<String> symbols, Updator updator) {

        RequestQueue queue = Volley.newRequestQueue(updator.getContext());

        for (String symbol : symbols) {
            Response.Listener<String> listener = new CoinMarketListener(symbol, updator);
            StringRequest request = new StringRequest(URL, listener, null);
            queue.add(request);
        }
    }
}
