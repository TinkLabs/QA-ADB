package com.tinklabs.handyadb;

import android.app.Application;
import android.content.Context;

/**
 * Created by swifty on 2/9/2016.
 */
public class ADBApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
