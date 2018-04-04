package com.github.cryptoaggregator.updator;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.cryptoaggregator.R;
import com.github.cryptoaggregator.listener.button.currbrowser.SaveCurrencyBrowserListenerFactory;
import com.github.cryptoaggregator.util.Logger;

/**
 * Created by pschoffer on 2018-04-03.
 */

public class CurrencyBrowserUpdator implements BrowserUpdator {
    private final Activity currencyBrowser;
    private final SaveCurrencyBrowserListenerFactory saveCurrencyBrowserListenerFactory;

    public CurrencyBrowserUpdator(SaveCurrencyBrowserListenerFactory saveCurrencyBrowserListenerFactory, Activity currencyBrowser) {
        this.saveCurrencyBrowserListenerFactory = saveCurrencyBrowserListenerFactory;
        this.currencyBrowser = currencyBrowser;
    }

    @Override
    public void update() {
        Logger.info("Updating currency browser.");

    }

    @Override
    public void updateWithError() {
        Logger.info("Updating currency browser with Error.");

        currencyBrowser.setContentView(R.layout.currency_browser);

        generateSpanningText(R.string.error);
        generateButtons();
    }

    @Override
    public void updateWithLoading() {
        Logger.info("Updating currency browser with Loading.");
        currencyBrowser.setContentView(R.layout.currency_browser);

        generateSpanningText(R.string.loading);
        generateButtons();
    }

    private void generateSpanningText(int textId) {
        final ViewGroup layout = currencyBrowser.findViewById(R.id.currencyBrowserLayout);

        final TextView loadingText = new TextView(currencyBrowser);

        loadingText.setText(textId);
        loadingText.setGravity(Gravity.CENTER);

        addSpanningElement(layout, loadingText);
    }

    private void generateButtons() {
        final ViewGroup layout = currencyBrowser.findViewById(R.id.currencyBrowserLayout);

        Button save = new Button(currencyBrowser);
        save.setText(R.string.save);


        View.OnClickListener onClickListener = saveCurrencyBrowserListenerFactory.create(currencyBrowser);
        save.setOnClickListener(onClickListener);

        addSpanningElement(layout, save);
    }

    private void addSpanningElement(ViewGroup layout, View element) {
        final TableRow row = new TableRow(currencyBrowser);
        final TableRow.LayoutParams rowParams = new TableRow.LayoutParams();
        rowParams.span = 2;
        rowParams.weight = 1;
        row.addView(element, rowParams);
        layout.addView(row);
    }
}
