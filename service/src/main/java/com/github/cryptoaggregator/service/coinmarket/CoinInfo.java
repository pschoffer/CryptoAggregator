package com.github.cryptoaggregator.service.coinmarket;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pschoffer on 2018-02-11.
 * This class holds information about specific coin
 */

public class CoinInfo {

    private String id;
    private String name;
    private String symbol;
    @SerializedName("price_usd")
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
