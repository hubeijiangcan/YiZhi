package com.mitbbs.yizhiapp.globle;

import com.mitbbs.sdk.globle.GlobalApplication;

/**
 * data:2018/1/2.
 *
 * @author:jc
 */
public class MApplication extends GlobalApplication{
    public static MApplication app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //初始化屏幕宽高

    }
}
