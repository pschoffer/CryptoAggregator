package com.github.cryptoaggregator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;

import com.github.cryptoaggregator.util.Logger;

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

        setContentView(R.layout.currency_browser);

        generateButtons();

    }

    private void generateButtons() {
        final ViewGroup layout = findViewById(R.id.currencyBrowserLayout);

        Button save = new Button(this);
        save.setText(R.string.save);

        // TODO do dedicated Listener I guess
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
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

