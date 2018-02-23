package com.mitbbs.sdk.utils;

import android.content.Context;
import android.os.Handler;

import com.mitbbs.sdk.globle.GlobalApplication;

/**
 * data:2017/12/26.
 *
 * @author:jc
 * <p></p>
 * app工具类
 */
public class AppUtils {

    /**
     * 获取上下文对象
     *
     * @return 上下文对象
     */
    public static Context getContext() {
        return GlobalApplication.getContext();
    }

    /**
     * 运行在主线程
     *
     * @param r 运行的Runnable对象
     */
    public static void runOnUIThread(Runnable r){
        if (isRunOnUIThread()){
            r.run();
        }else {
            getHandler().post(r);
        }
    }

    /**
     * 判断是否运行在主线程
     *
     * @return true：当前线程运行在主线程
     * fasle：当前线程没有运行在主线程
     */
    public static boolean isRunOnUIThread(){
        int myTid = android.os.Process.myTid();
        if (myTid == getMainThreadId()){
            return true;
        }
        return false;
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId(){
        return GlobalApplication.getMainThreadId();
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return GlobalApplication.getHandler();
    }
}
