package com.github.cryptoaggregator.listener.http;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.cryptoaggregator.updator.BrowserUpdator;
import com.github.cryptoaggregator.util.Logger;
import com.google.gson.Gson;

/**
 * Created by pschoffer on 2018-02-21.
 */

public class CoinMarketListListener implements Response.Listener<String>, Response.ErrorListener {

    private final BrowserUpdator browserUpdator;

    public CoinMarketListListener(BrowserUpdator currencyBrowserUpdator) {
        browserUpdator = currencyBrowserUpdator;
    }

    @Override
    public void onResponse(String response) {
        Logger.info("Got a response:\n" + response);

        final CoinInfo[] coinInfos = parseResponse(response);
        browserUpdator.update(coinInfos);
    }

    private CoinInfo[] parseResponse(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, CoinInfo[].class);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Logger.warn("Got an error response: " + error.getMessage());

        browserUpdator.updateWithError();
    }
}
