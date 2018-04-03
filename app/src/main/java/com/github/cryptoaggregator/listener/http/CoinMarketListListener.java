package com.github.cryptoaggregator.listener.http;

import com.android.volley.Response;
import com.github.cryptoaggregator.util.Logger;
import com.google.gson.Gson;

/**
 * Created by pschoffer on 2018-02-21.
 */

public class CoinMarketListListener implements Response.Listener<String>{


    @Override
    public void onResponse(String response) {
        Logger.info("Got a response:\n" + response);
    }

    private CoinInfo[] parseResponse(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, CoinInfo[].class);
    }
}
