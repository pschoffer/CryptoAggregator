package com.github.cryptoaggregator;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.cryptoaggregator.dependency.DaggerMainComponent;
import com.github.cryptoaggregator.dependency.MainComponent;
import com.github.cryptoaggregator.listener.button.configuration.MoreConfigListener;
import com.github.cryptoaggregator.listener.button.configuration.MoreConfigListenerFactory;
import com.github.cryptoaggregator.listener.button.configuration.SaveConfigListener;
import com.github.cryptoaggregator.listener.button.configuration.SaveConfigListenerFactory;
import com.github.cryptoaggregator.service.pref.PreferenceService;
import com.github.cryptoaggregator.service.pref.PreferenceServiceFactory;
import com.github.cryptoaggregator.service.pref.WidgetPreferences;
import com.github.cryptoaggregator.util.Logger;

import java.util.List;

import javax.inject.Inject;

/**
 * The configuration screen for the {@link MainWidget MainWidget} AppWidget.
 */
public class ConfigurationActivity extends Activity {
    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    @Inject
    public PreferenceServiceFactory preferenceServiceFactory;
    @Inject
    public SaveConfigListenerFactory saveConfigListenerFactory;
    @Inject
    public MoreConfigListenerFactory moreConfigListenerFactory;

    public ConfigurationActivity() {
        final MainComponent component = DaggerMainComponent.builder().build();
        component.inject(this);
    }

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

        TextView more = new TextView(this);
        more.setText(R.string.more);
        more.setGravity(Gravity.CENTER);
        final MoreConfigListener moreListener = moreConfigListenerFactory.create(this);
        more.setOnClickListener(moreListener);

        addSpanningElement(layout, more);

        Button save = new Button(this);
        save.setText(R.string.save);

        final SaveConfigListener saveConfigListener = saveConfigListenerFactory.create(this, appWidgetId);
        save.setOnClickListener(saveConfigListener);

        addSpanningElement(layout, save);
    }

    private void addSpanningElement(ViewGroup layout, View element) {
        final TableRow row = new TableRow(this);
        final TableRow.LayoutParams rowParams = new TableRow.LayoutParams();
        rowParams.span = 2;
        rowParams.weight = 1;
        row.addView(element, rowParams);
        layout.addView(row);
    }

    private void generateCoinsLines() {
        final PreferenceService preferenceService = preferenceServiceFactory.create(this);
        final WidgetPreferences widgetPreferences = preferenceService.loadWidgetPreferences(appWidgetId);

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

