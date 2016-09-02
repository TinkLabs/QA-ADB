package com.tinklabs.handyadb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import com.tinklabs.handyadb.utils.Permissions;
import com.tinklabs.handyadb.utils.ShellUtils;


/**
 * Created by swifty on 2/9/2016.
 */
public class ADBReceiver extends BroadcastReceiver {
    private static final String TAG = ADBReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        enableDebug();
    }

    protected void enableDebug() {
        try {
            if (Permissions.canWriteSecureSettings()) {
                Settings.Secure.putInt(ADBApplication.getContext().getContentResolver(), Settings.Global.ADB_ENABLED, 1);
            } else {
                ShellUtils.runCommand("settings put secure adb_enabled 1");
            }
        } catch (Exception e) {
            Log.w(TAG, "Exception in onCreate", e);
        }
    }
}
