package com.github.cryptoaggregator.listener.http;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.cryptoaggregator.util.Logger;
import com.google.gson.Gson;

/**
 * Created by pschoffer on 2018-02-21.
 */

public class CoinMarketListErrorListener implements Response.ErrorListener{


    @Override
    public void onErrorResponse(VolleyError error) {
        Logger.warn("Got an error response: " + error.getMessage());

    }
}
