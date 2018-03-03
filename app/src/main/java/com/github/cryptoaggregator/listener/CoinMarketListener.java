package com.github.cryptoaggregator.listener;

import com.android.volley.Response;
import com.github.cryptoaggregator.updator.Updator;
import com.github.cryptoaggregator.util.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by pschoffer on 2018-02-21.
 */

public class CoinMarketListener implements Response.Listener<String>{
    private final static String ERROR = "<ERROR>";

    private final Updator updator;
    private final String symbol;

    public CoinMarketListener(String symbol, Updator updator) {
        this.updator = updator;
        this.symbol = symbol;
    }

    @Override
    public void onResponse(String response) {
        Logger.info("Got a response:\n" + response);
        try {
            final CoinInfo coinInfo = parseResponse(response);

            updator.update(symbol, coinInfo.getPrice());
        } catch (JsonSyntaxException ex) {
            Logger.error("Failed to parse the response", ex);
            updator.update(symbol, ERROR);
        }
    }

    private CoinInfo parseResponse(String json) {
        final Gson gson = new Gson();
        final CoinInfo[] coinInfos = gson.fromJson(json, CoinInfo[].class);
        return coinInfos[0];
    }
}
