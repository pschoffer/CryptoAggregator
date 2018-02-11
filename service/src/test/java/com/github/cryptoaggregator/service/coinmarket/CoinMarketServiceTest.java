package com.github.cryptoaggregator.service.coinmarket;

import com.github.cryptoaggregator.common.Coin;
import com.github.cryptoaggregator.exception.CoinServiceException;
import com.github.cryptoaggregator.service.CoinService;
import com.github.cryptoaggregator.service.coinmarket.CoinMarketService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by pschoffer on 2018-02-11.
 */
public class CoinMarketServiceTest {

    private CoinService coinService;

    @Before
    public void setUp() throws Exception {
        coinService = new CoinMarketService();
    }

    @Test
    public void testFetchBitcoin() throws Exception {
        String price = coinService.fetchPrice(Coin.BITCOIN.name());

        Assert.assertNotNull(price);
        Assert.assertFalse(price.isEmpty());
    }

    @Test(expected = CoinServiceException.class)
    public void testFetchUnknown() throws Exception {
        coinService.fetchPrice("WRONG_NAME");
    }
}