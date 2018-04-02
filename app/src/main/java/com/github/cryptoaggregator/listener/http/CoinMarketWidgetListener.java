package com.github.cryptoaggregator.listener.http;

import com.android.volley.Response;
import com.github.cryptoaggregator.updator.WidgetUpdator;
import com.github.cryptoaggregator.util.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by pschoffer on 2018-02-21.
 */

public class CoinMarketWidgetListener implements Response.Listener<String>{
    private final WidgetUpdator widgetUpdator;
    private final String coin;

    public CoinMarketWidgetListener(String coin, WidgetUpdator widgetUpdator) {
        this.widgetUpdator = widgetUpdator;
        this.coin = coin;
    }

    @Override
    public void onResponse(String response) {
        Logger.info("Got a response:\n" + response);
        try {
            final CoinInfo coinInfo = parseResponse(response);

            widgetUpdator.update(coin, coinInfo);
        } catch (JsonSyntaxException ex) {
            Logger.error("Failed to parse the response", ex);
            widgetUpdator.update(coin, null);
        }
    }

    private CoinInfo parseResponse(String json) {
        final Gson gson = new Gson();
        final CoinInfo[] coinInfos = gson.fromJson(json, CoinInfo[].class);
        return coinInfos[0];
    }
}
