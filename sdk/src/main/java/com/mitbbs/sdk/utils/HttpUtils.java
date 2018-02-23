package com.mitbbs.sdk.utils;

import android.os.Build;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;

/**
 * data:2018/1/3.
 *
 * @author:jc
 */
public class HttpUtils {
    /**
     * 获取UserAgent
     *
     * @return UserAgent
     */
    @Nullable
    public static String getUserAgent(){
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            try {
                userAgent = WebSettings.getDefaultUserAgent(AppUtils.getContext());
            }
            catch (Exception e){
                userAgent = System.getProperty("http.agent");
            }
        }else {
            userAgent = System.getProperty("http.agent");
        }

        StringBuffer sb = new StringBuffer();
        for (int i =0,length = userAgent.length();i < length;i++){
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
