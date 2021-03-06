package com.github.cryptoaggregator.service.android;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.github.cryptoaggregator.R;
import com.github.cryptoaggregator.util.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by pschoffer on 2018-03-06.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({PendingIntent.class, Logger.class})
public class WidgetRemoteViewsServiceTest {
    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";
    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";
    public static final int APP_WIDGET_ID = 1;

    private RemoteViewsService service;

    @Mock
    RemoteViews remoteViews;
    @Mock
    Context context;
    @Mock
    PendingIntent pendingIntent;
    @Mock
    IntentFactory intentFactory;
    @Mock
    private Intent intent;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(PendingIntent.class);
        PowerMockito.mockStatic(Logger.class);

        when(PendingIntent.getActivity(any(Context.class), anyInt(), any(Intent.class), anyInt())).thenReturn(pendingIntent);
        when(intentFactory.createIntent(any(Context.class), any(Class.class))).thenReturn(intent);
        service = new WidgetRemoteViewsService(context, intentFactory);
    }

    @Test
    public void testSetKeyValues() throws Exception {
        Map<String, String> keyValues = new HashMap<>();
        keyValues.put(KEY1, VALUE1);
        keyValues.put(KEY2, VALUE2);

        service.setContent(remoteViews, keyValues);

        verify(remoteViews).setTextViewText(R.id.text_symbol, KEY1+"\n"+KEY2);
        verify(remoteViews).setTextViewText(R.id.text_value, VALUE1+"\n"+VALUE2);

    }

    @Test
    public void testSetIntent() throws Exception {
        service.setIntents(remoteViews, APP_WIDGET_ID);

        verify(remoteViews).setOnClickPendingIntent(eq(R.id.text_symbol), any(PendingIntent.class));
    }
}