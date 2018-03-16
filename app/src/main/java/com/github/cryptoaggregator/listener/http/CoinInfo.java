package com.github.cryptoaggregator.listener.http;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by pschoffer on 2018-02-11.
 * This class holds information about specific coin
 */

public class CoinInfo {

    private String id;
    private String name;
    private String symbol;
    @SerializedName("price_usd")
    private BigDecimal price;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
