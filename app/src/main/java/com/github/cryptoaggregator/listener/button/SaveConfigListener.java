package com.github.cryptoaggregator.listener.button;

import android.content.Context;
import android.view.View;

import com.github.cryptoaggregator.util.Logger;

/**
 * Created by pschoffer on 2018-03-15.
 */

public class SaveConfigListener implements View.OnClickListener {
    private final Context context;
    private final int appWidgetId;

    SaveConfigListener(Context context, int appWidgetId) {
        this.context = context;
        this.appWidgetId = appWidgetId;
    }

    @Override
    public void onClick(View view) {
        Logger.info("Persisting configuration for widget " + appWidgetId);
//        // When the button is clicked, store the string locally
//        String widgetText = mAppWidgetText.getText().toString();
//        saveTitlePref(context, mAppWidgetId, widgetText);
//
//        // It is the responsibility of the configuration activity to update the app widget
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//        MainWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);
//
//        // Make sure we pass back the original appWidgetId
//        Intent resultValue = new Intent();
//        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
//        setResult(RESULT_OK, resultValue);
//        finish();
    }
}
