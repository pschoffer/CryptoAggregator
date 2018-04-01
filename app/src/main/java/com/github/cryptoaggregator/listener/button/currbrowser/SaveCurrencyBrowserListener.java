package com.github.cryptoaggregator.listener.button.currbrowser;

import android.app.Activity;
import android.view.View;

import com.github.cryptoaggregator.util.Logger;

/**
 * Created by pschoffer on 2018-04-01.
 */

public class SaveCurrencyBrowserListener implements View.OnClickListener {
    private final Activity activity;

    SaveCurrencyBrowserListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        Logger.info("Closing currency browser.");
        activity.finish();
    }
}
