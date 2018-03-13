package com.github.cryptoaggregator.updator;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.widget.RemoteViews;

import com.github.cryptoaggregator.listener.CoinInfo;
import com.github.cryptoaggregator.service.android.WidgetRemoteViewsService;
import com.github.cryptoaggregator.service.android.WidgetRemoteViewsServiceFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by pschoffer on 2018-03-05.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AppWidgetManager.class)
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
    private WidgetRemoteViewsService widgetRemoteViewsService;
    @Mock
    private WidgetRemoteViewsServiceFactory widgetRemoteViewsServiceFactory;
    @Mock
    private RemoteViews remoteViews;

    private CoinInfo coinInfo1;
    private CoinInfo coinInfo2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(AppWidgetManager.class);

        coinInfo1 = generateCoinInfo(COIN_1);
        coinInfo2 = generateCoinInfo(COIN_2);

        when(AppWidgetManager.getInstance(any(Context.class))).thenReturn(widgetManager);
        when(widgetRemoteViewsServiceFactory.create(any(Context.class))).thenReturn(widgetRemoteViewsService);
        when(widgetRemoteViewsService.createRemoteViews()).thenReturn(remoteViews);
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
        updator = new MainWidgetUpdator(context, WIDGET_ID, coins, widgetRemoteViewsServiceFactory);

        updator.update(COIN_1, coinInfo1);

        verify(widgetManager, never()).updateAppWidget(Mockito.eq(WIDGET_ID), any(RemoteViews.class));
    }
    
    @Test
    public void testDoesUpdateOnAllResults() throws Exception {
        final List<String> coins = Arrays.asList(COIN_1, COIN_2);
        updator = new MainWidgetUpdator(context, WIDGET_ID, coins, widgetRemoteViewsServiceFactory);

        updator.update(COIN_1, coinInfo1);
        updator.update(COIN_2, coinInfo2);

        verify(widgetRemoteViewsService).setContent(any(RemoteViews.class), anyMap());
        verify(widgetManager).updateAppWidget(WIDGET_ID, remoteViews);
    }
}