package com.github.cryptoaggregator.service;

/**
 * Created by pschoffer on 2018-02-11.
 * This service aim is to query information about cryptocurrencies.
 */

public interface CoinService {
    String fetchPrice(String id);
}
