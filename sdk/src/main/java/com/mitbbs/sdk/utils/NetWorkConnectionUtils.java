package com.mitbbs.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * data:2018/1/3.
 *
 * @author:jc
 */
public class NetWorkConnectionUtils {

    private static final String TAG = "NetWorkConnectionUtils";

    private NetWorkConnectionUtils(){

    }

    public static boolean isConnected(Context context){
        if (context != null){
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            if (null == cm) {
                return false;
            }

            NetworkInfo info = cm.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;

    }

    public static boolean isNetworkConnected(Context context){
        if (context != null){
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null){
                return info.isAvailable();
            }
        }
        return false;
    }
}
