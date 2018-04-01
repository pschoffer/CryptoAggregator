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
import com.github.cryptoaggregator.listener.button.MoreConfigListener;
import com.github.cryptoaggregator.listener.button.MoreConfigListenerFactory;
import com.github.cryptoaggregator.listener.button.SaveConfigListener;
import com.github.cryptoaggregator.listener.button.SaveConfigListenerFactory;
import com.github.cryptoaggregator.service.widget.pref.WidgetPreferenceService;
import com.github.cryptoaggregator.service.widget.pref.WidgetPreferenceServiceFactory;
import com.github.cryptoaggregator.service.widget.pref.WidgetPreferences;
import com.github.cryptoaggregator.util.Logger;

import java.util.List;

import javax.inject.Inject;

/**
 * The configuration screen for the {@link MainWidget MainWidget} AppWidget.
 */
public class CurrencyBrowserActivity extends Activity {
    public CurrencyBrowserActivity() {
//        final MainComponent component = DaggerMainComponent.builder().build();
//        component.inject(this);
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Logger.info("Creating Currency Browser activity.");
    }

}

