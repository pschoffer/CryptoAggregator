package com.github.cryptoaggregator.updator;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.cryptoaggregator.R;
import com.github.cryptoaggregator.listener.button.currbrowser.ClickCurrencyBrowserListener;
import com.github.cryptoaggregator.listener.button.currbrowser.ClickCurrencyBrowserListenerFactory;
import com.github.cryptoaggregator.listener.button.currbrowser.SaveCurrencyBrowserListenerFactory;
import com.github.cryptoaggregator.listener.http.CoinInfo;
import com.github.cryptoaggregator.service.pref.GlobalPreferences;
import com.github.cryptoaggregator.service.pref.PreferenceService;
import com.github.cryptoaggregator.service.pref.PreferenceServiceFactory;
import com.github.cryptoaggregator.util.Logger;

import java.util.List;

/**
 * Created by pschoffer on 2018-04-03.
 */

public class CurrencyBrowserUpdator implements BrowserUpdator {
    private static final int CURRENCY_BROWSER_LAYOUT_ID = R.layout.currency_browser;
    private static final int CURRENCY_BROWSER_COLUMN_COUNT = 1;
    private final Activity currencyBrowser;
    private final SaveCurrencyBrowserListenerFactory saveCurrencyBrowserListenerFactory;
    private final ClickCurrencyBrowserListenerFactory clickCurrencyBrowserListenerFactory;
    private final PreferenceServiceFactory preferenceServiceFactory;

    public CurrencyBrowserUpdator(SaveCurrencyBrowserListenerFactory saveCurrencyBrowserListenerFactory, ClickCurrencyBrowserListenerFactory clickCurrencyBrowserListenerFactory, PreferenceServiceFactory preferenceServiceFactory, Activity currencyBrowser) {
        this.saveCurrencyBrowserListenerFactory = saveCurrencyBrowserListenerFactory;
        this.clickCurrencyBrowserListenerFactory = clickCurrencyBrowserListenerFactory;
        this.preferenceServiceFactory = preferenceServiceFactory;
        this.currencyBrowser = currencyBrowser;
    }

    @Override
    public void update(CoinInfo[] coinInfos) {
        Logger.info("Updating currency browser.");

        currencyBrowser.setContentView(CURRENCY_BROWSER_LAYOUT_ID);

        generateCurrencyList(coinInfos);
        generateButtons();
    }

    private void generateCurrencyList(CoinInfo[] coinInfos) {
        final ViewGroup layout = currencyBrowser.findViewById(R.id.currencyBrowserLayout);

        final PreferenceService preferenceService = preferenceServiceFactory.create(currencyBrowser);
        final GlobalPreferences globalPreferences = preferenceService.loadGlobalPreferences();
        final List<String> activeCurrencies = globalPreferences.getActiveCurrencies();

        for (CoinInfo coinInfo : coinInfos) {
            if (activeCurrencies.contains(coinInfo.getId())) {
                continue;
            }

            final ClickCurrencyBrowserListener clickCurrencyBrowserListener = clickCurrencyBrowserListenerFactory.create(coinInfo.getId(), currencyBrowser);

            TableRow row = new TableRow(currencyBrowser);

            TextView coin = new TextView(currencyBrowser);
            final String label = String.format("[%s] %s", coinInfo.getSymbol(), coinInfo.getName());
            coin.setText(label);
            coin.setOnClickListener(clickCurrencyBrowserListener);
            row.addView(coin, TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

            layout.addView(row);
        }


    }

    @Override
    public void updateWithError() {
        Logger.info("Updating currency browser with Error.");

        currencyBrowser.setContentView(CURRENCY_BROWSER_LAYOUT_ID);

        generateSpanningText(R.string.error);
        generateButtons();
    }

    @Override
    public void updateWithLoading() {
        Logger.info("Updating currency browser with Loading.");
        currencyBrowser.setContentView(CURRENCY_BROWSER_LAYOUT_ID);

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
        rowParams.span = CURRENCY_BROWSER_COLUMN_COUNT;
        rowParams.weight = 1;
        row.addView(element, rowParams);
        layout.addView(row);
    }
}
