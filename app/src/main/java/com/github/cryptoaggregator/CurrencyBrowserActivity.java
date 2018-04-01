package com.github.cryptoaggregator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;

import com.github.cryptoaggregator.dependency.CurrencyBrowserComponent;
import com.github.cryptoaggregator.dependency.DaggerCurrencyBrowserComponent;
import com.github.cryptoaggregator.listener.button.currbrowser.SaveCurrencyBrowserListenerFactory;
import com.github.cryptoaggregator.util.Logger;

import javax.inject.Inject;

/**
 * The configuration screen for the {@link MainWidget MainWidget} AppWidget.
 */
public class CurrencyBrowserActivity extends Activity {
    @Inject
    public SaveCurrencyBrowserListenerFactory saveCurrencyBrowserListenerFactory;

    public CurrencyBrowserActivity() {
        final CurrencyBrowserComponent component = DaggerCurrencyBrowserComponent.builder().build();
        component.inject(this);
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Logger.info("Creating Currency Browser activity.");

        setContentView(R.layout.currency_browser);

        generateButtons();

    }

    private void generateButtons() {
        final ViewGroup layout = findViewById(R.id.currencyBrowserLayout);

        Button save = new Button(this);
        save.setText(R.string.save);


        View.OnClickListener onClickListener = saveCurrencyBrowserListenerFactory.create(this);
        save.setOnClickListener(onClickListener);

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

}

