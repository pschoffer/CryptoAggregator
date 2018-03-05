package com.github.cryptoaggregator.listener;

import android.util.Log;

import com.github.cryptoaggregator.updator.Updator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by pschoffer on 2018-02-21.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Log.class)
public class CoinMarketListenerTest {
    private static final BigDecimal PRICE_USD = new BigDecimal(10.1);
    private static final String SYMBOL = "btc";
    private static final String COIN = "bitcoin";

    @Mock
    private Updator updator;

    private CoinMarketListener listener;

    private String response;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(Log.class);

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        response = "[ {\"price_usd\": \"" + decimalFormat.format(PRICE_USD) + "\", \"symbol\": \"" + SYMBOL + "\"} ]";
        listener = new CoinMarketListener(COIN, updator);
    }

    @Test
    public void testOnResponseContainsResult() throws Exception {
        listener.onResponse(response);

        ArgumentCaptor<CoinInfo> argumentCaptor = ArgumentCaptor.forClass(CoinInfo.class);
        verify(updator).update(eq(COIN), argumentCaptor.capture());
        final CoinInfo state = argumentCaptor.getValue();

        Assert.assertEquals(PRICE_USD.setScale(1, BigDecimal.ROUND_HALF_UP), state.getPrice());
        Assert.assertEquals(SYMBOL, state.getSymbol());
    }

    @Test
    public void testOnResponseWrongFormat() throws Exception {
        listener.onResponse("Wrong");

        ArgumentCaptor<CoinInfo> argumentCaptor = ArgumentCaptor.forClass(CoinInfo.class);
        verify(updator).update(eq(COIN), argumentCaptor.capture());
        final CoinInfo state = argumentCaptor.getValue();

        Assert.assertNull(state);
    }
}