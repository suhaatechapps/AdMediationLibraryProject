package com.zee.admediationlibraryproject;

import android.app.Application;

import com.zee.suhaatecs.mediation.TrueAdManager;
import com.zee.suhaatecs.mediation.TrueConstants;
import com.zee.suhaatecs.mediation.adlimits.TrueAntiAdLimit;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TrueAdManager.INSTANCE.zInitializeAds(this);
        try {
            if (TrueConstants.INSTANCE.isNetworkAvailable(TrueAdManager.context) &&
                    TrueConstants.INSTANCE.isNetworkSpeedHigh()) {
                TrueAntiAdLimit.getInstance()
                        .init(this, "https://suhaatech.com/AdsId/testads.json");
            }
        } catch (Exception ignored) {
        }
    }
}