package com.github.cryptoaggregator.listener;

import com.github.cryptoaggregator.updator.Updator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by pschoffer on 2018-02-21.
 */
public class CoinMarketListenerTest {
    private static final String ERROR = "<ERROR>";
    private static final String PRICE = "10.1";
    private static final String RESPONSE = "[ {\"price_usd\": \"" + PRICE + "\"} ]";

    @Mock
    private Updator updator;

    private CoinMarketListener listener;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        listener = new CoinMarketListener(updator);
    }

    @Test
    public void testOnResponseContainsResult() throws Exception {
        listener.onResponse(RESPONSE);

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(updator).update(argumentCaptor.capture());
        final String result = argumentCaptor.getValue();

        Assert.assertEquals(PRICE, result);
    }

    @Test
    public void testOnResponseWrongFormat() throws Exception {
        listener.onResponse("Wrong");

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(updator).update(argumentCaptor.capture());
        final String result = argumentCaptor.getValue();

        Assert.assertEquals(ERROR, result);
    }
}