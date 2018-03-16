package com.github.cryptoaggregator.listener.http;

import com.android.volley.Response;
import com.github.cryptoaggregator.updator.Updator;
import com.github.cryptoaggregator.util.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by pschoffer on 2018-02-21.
 */

public class CoinMarketListener implements Response.Listener<String>{
    private final Updator updator;
    private final String coin;

    public CoinMarketListener(String coin, Updator updator) {
        this.updator = updator;
        this.coin = coin;
    }

    @Override
    public void onResponse(String response) {
        Logger.info("Got a response:\n" + response);
        try {
            final CoinInfo coinInfo = parseResponse(response);

            updator.update(coin, coinInfo);
        } catch (JsonSyntaxException ex) {
            Logger.error("Failed to parse the response", ex);
            updator.update(coin, null);
        }
    }

    private CoinInfo parseResponse(String json) {
        final Gson gson = new Gson();
        final CoinInfo[] coinInfos = gson.fromJson(json, CoinInfo[].class);
        return coinInfos[0];
    }
}
