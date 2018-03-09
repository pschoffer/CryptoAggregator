package com.github.cryptoaggregator.service.android;

import android.widget.RemoteViews;

import com.github.cryptoaggregator.R;

import java.util.Map;

/**
 * Created by pschoffer on 2018-03-05.
 */

public class WidgetRemoteViewsService implements RemoteViewsService {
    private final String packageName;

    public WidgetRemoteViewsService(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public RemoteViews createRemoteViews() {
        return new RemoteViews(packageName, R.layout.main_widget);
    }

    @Override
    public void setContent(RemoteViews target, Map<String, String> content) {
        StringBuilder keys = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (Map.Entry<String, String> keyValue : content.entrySet()) {
            if (!keys.toString().isEmpty()) {
                keys.append("\n");
                values.append("\n");
            }
            keys.append(keyValue.getKey());
            values.append(keyValue.getValue());
        }
        target.setTextViewText(R.id.text_symbol, keys.toString());
        target.setTextViewText(R.id.text_value, values.toString());
    }
}
