package com.rz.librarycore.apppackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Rz Rasel on 2016/11/25.
 */

public class AppPackageInfo {
    public static String getVersionName(Context argContext) {
        PackageManager manager = argContext.getPackageManager();
        try {
            //PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            PackageInfo packageInfo = manager.getPackageInfo(argContext.getPackageName(), PackageManager.GET_META_DATA);
            //return packageInfo.versionCode;
            //packageInfo.packageName;
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    public static int getVersionCode(Context argContext) {
        PackageManager manager = argContext.getPackageManager();
        try {
            //PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            PackageInfo packageInfo = manager.getPackageInfo(argContext.getPackageName(), PackageManager.GET_META_DATA);
            //return packageInfo.versionCode;
            //packageInfo.packageName;
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }
}