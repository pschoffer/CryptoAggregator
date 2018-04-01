package com.github.cryptoaggregator.listener.button.configuration;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.github.cryptoaggregator.CurrencyBrowserActivity;
import com.github.cryptoaggregator.util.Logger;

/**
 * Created by pschoffer on 2018-03-15.
 */

public class MoreConfigListener implements View.OnClickListener {
    private final Activity configActivity;

    MoreConfigListener(Activity context) {
        this.configActivity = context;
    }

    @Override
    public void onClick(View view) {
        Logger.info("Processing more currencies loader");

        Intent intent = new Intent(configActivity, CurrencyBrowserActivity.class);
        configActivity.startActivity(intent);
    }
}
