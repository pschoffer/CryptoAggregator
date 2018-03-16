package com.github.cryptoaggregator.listener.button;

import android.content.Context;
import android.view.View;

/**
 * Created by pschoffer on 2018-03-15.
 */

public class SaveConfigListener implements View.OnClickListener {
    private final Context context;

    public SaveConfigListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {

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
