package com.github.cryptoaggregator.service.coinmarket;

import com.github.cryptoaggregator.exception.CoinServiceException;
import com.github.cryptoaggregator.service.CoinService;
import com.google.gson.Gson;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by pschoffer on 2018-02-11.
 */

public class CoinMarketService implements CoinService {
    private static final String COIN_MARKET_URL = "https://api.coinmarketcap.com/v1";
    private static final String COIN_MARKET_TICKER_URL = COIN_MARKET_URL + "/ticker/";

    @Override
    public String fetchPrice(String id) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(COIN_MARKET_TICKER_URL + id);
        String response;
        try {
            response = httpclient.execute(httpGet, new BasicResponseHandler());
        } catch (IOException e) {
            throw new CoinServiceException("Reading response fail.", e);
        }

        final CoinInfo coinInfo = parseResponse(response);

        return coinInfo.getPrice();
    }

    private CoinInfo parseResponse(String json) {
        final Gson gson = new Gson();
        final CoinInfo[] coinInfos = gson.fromJson(json, CoinInfo[].class);
        return coinInfos[0];
    }


}
