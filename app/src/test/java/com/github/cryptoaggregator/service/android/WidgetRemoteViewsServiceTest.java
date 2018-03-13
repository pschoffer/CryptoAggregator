package com.github.cryptoaggregator.service.android;

import android.content.Context;
import android.widget.RemoteViews;

import com.github.cryptoaggregator.R;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

/**
 * Created by pschoffer on 2018-03-06.
 */
public class WidgetRemoteViewsServiceTest {
    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";
    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";

    private RemoteViewsService service;

    @Mock
    RemoteViews remoteViews;
    @Mock
    Context context;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new WidgetRemoteViewsService(context);
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
}