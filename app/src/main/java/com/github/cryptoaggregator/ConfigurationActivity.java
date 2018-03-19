package com.github.cryptoaggregator;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.cryptoaggregator.dependency.DaggerMainComponent;
import com.github.cryptoaggregator.dependency.MainComponent;
import com.github.cryptoaggregator.listener.button.SaveConfigListener;
import com.github.cryptoaggregator.listener.button.SaveConfigListenerFactory;
import com.github.cryptoaggregator.service.pref.WidgetPreferenceService;
import com.github.cryptoaggregator.service.pref.WidgetPreferenceServiceFactory;
import com.github.cryptoaggregator.service.pref.WidgetPreferences;
import com.github.cryptoaggregator.util.Logger;

import java.util.List;

import javax.inject.Inject;

/**
 * The configuration screen for the {@link MainWidget MainWidget} AppWidget.
 */
public class ConfigurationActivity extends Activity {
//
//    private static final String PREFS_NAME = "com.github.cryptoaggregator.MainWidget";
//    private static final String PREF_PREFIX_KEY = "appwidget_";
    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    @Inject
    public WidgetPreferenceServiceFactory widgetPreferenceServiceFactory;
    @Inject
    public SaveConfigListenerFactory saveConfigListenerFactory;

    public ConfigurationActivity() {
        final MainComponent component = DaggerMainComponent.builder().build();
        component.inject(this);
    }
//    EditText mAppWidgetText;
//    View.OnClickListener mOnClickListener = new View.OnClickListener() {
//        public void onClick(View v) {
//            final Context context = ConfigurationActivity.this;
//
//            // When the button is clicked, store the string locally
//            String widgetText = mAppWidgetText.getText().toString();
//            saveTitlePref(context, mAppWidgetId, widgetText);
//
//            // It is the responsibility of the configuration activity to update the app widget
//            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//            MainWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);
//
//            // Make sure we pass back the original appWidgetId
//            Intent resultValue = new Intent();
//            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
//            setResult(RESULT_OK, resultValue);
//            finish();
//        }
//    };
//
//    public ConfigurationActivity() {
//        super();
//    }
//
//    // Write the prefix to the SharedPreferences object for this widget
//    static void saveTitlePref(Context context, int appWidgetId, String text) {
//        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
//        prefs.putString(PREF_PREFIX_KEY + appWidgetId, text);
//        prefs.apply();
//    }
//
//    // Read the prefix from the SharedPreferences object for this widget.
//    // If there is no preference saved, get the default from a resource
//    static String loadTitlePref(Context context, int appWidgetId) {
//        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
//        String titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null);
//        if (titleValue != null) {
//            return titleValue;
//        } else {
//            return context.getString(R.string.appwidget_text);
//        }
//    }
//
//    static void deleteTitlePref(Context context, int appWidgetId) {
//        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
//        prefs.remove(PREF_PREFIX_KEY + appWidgetId);
//        prefs.apply();
//    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Logger.info("Creating Config activity.");
        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            appWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        setContentView(R.layout.configure);

        generateCoinsLines();
        generateButtons();
    }

    private void generateButtons() {
        final ViewGroup layout = findViewById(R.id.configureLayout);
        final TableRow row = new TableRow(this);

        Button save = new Button(this);
        save.setText(R.string.save);

        final SaveConfigListener saveConfigListener = saveConfigListenerFactory.create(this, appWidgetId);
        save.setOnClickListener(saveConfigListener);

        final TableRow.LayoutParams saveParams = new TableRow.LayoutParams();
        saveParams.span = 2;
        saveParams.weight = 1;
        row.addView(save, saveParams);
        layout.addView(row);
    }

    private void generateCoinsLines() {
        final WidgetPreferenceService widgetPreferenceService = widgetPreferenceServiceFactory.create(this);
        final WidgetPreferences widgetPreferences = widgetPreferenceService.loadPreferences(appWidgetId);

        final List<String> availableCoins = widgetPreferences.getCurrencies();
        final ViewGroup layout = findViewById(R.id.configureLayout);
        for (String availableCoin : availableCoins) {
            TableRow row = new TableRow(this);

            CheckBox box = new CheckBox(this);
            box.setChecked(widgetPreferences.isEnabled(availableCoin));
            row.addView(box, TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

            TextView label = new TextView(this);
            label.setText(availableCoin);
            row.addView(label, TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

            layout.addView(row);
        }
    }
}

