package com.github.cryptoaggregator.listener.http;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.cryptoaggregator.updator.WidgetUpdator;
import com.github.cryptoaggregator.util.Logger;

/**
 * Created by pschoffer on 2018-02-21.
 */

public class CoinMarketWidgeErrortListener implements  Response.ErrorListener{
    private final String coin;
    private final WidgetUpdator widgetUpdator;

    public CoinMarketWidgeErrortListener(String coin, WidgetUpdator widgetUpdator) {
        this.coin = coin;
        this.widgetUpdator = widgetUpdator;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Logger.warn("Got an error response: " + error.getMessage());

        widgetUpdator.update(coin, null);
    }
}
