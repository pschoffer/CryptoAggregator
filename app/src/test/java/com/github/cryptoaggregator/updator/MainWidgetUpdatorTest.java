package com.github.cryptoaggregator.updator;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.widget.RemoteViews;

import com.github.cryptoaggregator.service.android.RemoteViewsFactory;
import com.github.cryptoaggregator.listener.CoinInfo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by pschoffer on 2018-03-05.
 */
public class MainWidgetUpdatorTest {
    private final static int WIDGET_ID = 1;
    private final static String COIN_1 = "coin1";
    private final static String COIN_2 = "coin2";

    private MainWidgetUpdator updator;
    @Mock
    private Context context;
    @Mock
    private AppWidgetManager widgetManager;
    @Mock
    private RemoteViewsFactory remoteViewsFactory;
    @Mock
    private RemoteViews remoteViews;

    private CoinInfo coinInfo1;
    private CoinInfo coinInfo2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        coinInfo1 = generateCoinInfo(COIN_1);
        coinInfo2 = generateCoinInfo(COIN_2);

        when(remoteViewsFactory.createInstance(anyString(), anyInt())).thenReturn(remoteViews);
    }

    private CoinInfo generateCoinInfo(String coinId) {
        CoinInfo coinInfo = new CoinInfo();
        coinInfo.setId(coinId);
        coinInfo.setSymbol(coinId);
        coinInfo.setName(coinId);
        coinInfo.setPrice(BigDecimal.TEN);
        return coinInfo;
    }

    @Test
    public void testDoesntUpdateOnFirstResult() throws Exception {
        final List<String> coins = Arrays.asList(COIN_1, COIN_2);
        updator = new MainWidgetUpdator(context, widgetManager, WIDGET_ID, remoteViewsFactory, coins);

        updator.update(COIN_1, coinInfo1);

        verify(widgetManager, never()).updateAppWidget(Mockito.eq(WIDGET_ID), any(RemoteViews.class));
    }
    
    @Test
    public void testDoesUpdateOnAllResults() throws Exception {
        final List<String> coins = Arrays.asList(COIN_1, COIN_2);
        updator = new MainWidgetUpdator(context, widgetManager, WIDGET_ID, remoteViewsFactory, coins);

        updator.update(COIN_1, coinInfo1);
        updator.update(COIN_2, coinInfo2);

        verify(remoteViews, times(2)).setTextViewText(anyInt(), any(CharSequence.class));
        verify(widgetManager).updateAppWidget(WIDGET_ID, remoteViews);
    }
}