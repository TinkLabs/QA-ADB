package com.tinklabs.handyadb.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import com.tinklabs.handyadb.ADBApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kriz on 7/3/2016.
 */
public class Permissions {
    protected static Boolean isSystemApp;
    protected static Map<String, Boolean> permissionCache;

    public static final int PERMISSIONS_REQUEST_WRITE_CALL_LOG = 100;
    public static final int PERMISSIONS_REQUEST_READ_CALL_LOG = 101;
    public static final int PERMISSIONS_REQUEST_CALL = 102;
    public static final int PERMISSIONS_REQUEST_READ_CONTACT = 103;
    public static final int PERMISSIONS_REQUEST_WRITE_CONTACT = 104;

    protected static Map<String, Boolean> getPermissionCache() {
        if (permissionCache == null) {
            permissionCache = new HashMap<>();
        }
        return permissionCache;
    }

    public static boolean canWriteSecureSettings() {
        return hasPermission(Manifest.permission.WRITE_SECURE_SETTINGS);
    }

    public static boolean canReadPhoneState() {
        return hasPermission(Manifest.permission.READ_PHONE_STATE);
    }

    public static boolean canGetConnectionInfo() {
        return hasPermission(Manifest.permission.ACCESS_NETWORK_STATE) && hasPermission(Manifest.permission.ACCESS_WIFI_STATE);
    }

    public static boolean canAccessCoarseLocation() {
        return hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    public static boolean canAccessFineLocation() {
        return hasPermission(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public static boolean checkReadContactPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermission(Manifest.permission.READ_CONTACTS)) {
                if (context instanceof Activity)
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            PERMISSIONS_REQUEST_READ_CONTACT);
                return false;
            }
        }
        return true;
    }

    public static boolean checkWriteContactPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermission(Manifest.permission.WRITE_CONTACTS)) {
                if (context instanceof Activity)
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.WRITE_CONTACTS},
                            PERMISSIONS_REQUEST_WRITE_CONTACT);
                return false;
            }
        }
        return true;
    }

    public static boolean checkReadCallLogPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermission(Manifest.permission.READ_CALL_LOG)) {
                if (context instanceof Activity)
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.READ_CALL_LOG},
                            PERMISSIONS_REQUEST_READ_CALL_LOG);
                return false;
            }
        }
        return true;
    }

    public static boolean checkWriteCallLogPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermission(Manifest.permission.WRITE_CALL_LOG)) {
                if (context instanceof Activity)
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.WRITE_CALL_LOG},
                            PERMISSIONS_REQUEST_WRITE_CALL_LOG);
                return false;
            }
        }
        return true;
    }

    public static boolean checkCallPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermission(Manifest.permission.CALL_PHONE)) {
                if (context instanceof Activity)
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.CALL_PHONE},
                            PERMISSIONS_REQUEST_CALL);
                return false;
            }
        }
        return true;
    }

    /**
     * @param fragment
     * @return false means Permission has not been granted and will show permission dialog, true means permission has been granted.
     */
    public static boolean checkReadContactPermission(Fragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermission(Manifest.permission.READ_CONTACTS)) {
                fragment.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACT);
                return false;
            }
        }
        return true;
    }

    public static boolean checkWriteContactPermission(Fragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermission(Manifest.permission.WRITE_CONTACTS)) {
                fragment.requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS}, PERMISSIONS_REQUEST_WRITE_CONTACT);
                return false;
            }
        }
        return true;
    }

    public static boolean checkReadCallLogPermission(Fragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermission(Manifest.permission.READ_CALL_LOG)) {
                fragment.requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG}, PERMISSIONS_REQUEST_READ_CALL_LOG);
                return false;
            }
        }
        return true;
    }

    public static boolean checkWriteCallLogPermission(Fragment fragment) {
        if (!hasPermission(Manifest.permission.WRITE_CALL_LOG)) {
            fragment.requestPermissions(new String[]{Manifest.permission.WRITE_CALL_LOG}, PERMISSIONS_REQUEST_WRITE_CALL_LOG);
            return false;
        }
        return true;
    }

    public static boolean checkCallPermission(Fragment fragment) {
        if (!hasPermission(Manifest.permission.CALL_PHONE)) {
            fragment.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_CALL);
            return false;
        }
        return true;
    }

    public static boolean hasPermission(String permission) {
        // use cached results
        Boolean cached = getPermissionCache().get(permission);
        if (cached != null) return cached;

        // check permission
        Context ctx = ADBApplication.getContext();
        int res = ctx.checkCallingOrSelfPermission(permission);
        boolean hasPermission = res == PackageManager.PERMISSION_GRANTED;

        //try to grantPermission only for 6.0
        if (!hasPermission) hasPermission = grantPermission(permission);

        // update cache
        getPermissionCache().put(permission, hasPermission);
        return hasPermission;
    }

    private static boolean grantPermission(String permission) {
        boolean grantSuccess = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            grantSuccess = ShellUtils.runCommand("pm grant " + ADBApplication.getContext().getPackageName() + " " + permission) == 0;
        }
        return grantSuccess;
    }
}
