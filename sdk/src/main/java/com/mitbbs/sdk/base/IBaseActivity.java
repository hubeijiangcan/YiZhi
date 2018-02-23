package com.mitbbs.sdk.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * data:2018/2/2.
 *
 * @author:jc
 */
public interface IBaseActivity extends IBaseView{
    /**
     * 跳往新的Activity
     *
     * @param clz 要跳往的Activity
     */
    void startNewActivity(@NonNull Class<?> clz);

    /**
     * 跳往新的Activity
     *
     * @param clz    要跳往的Activity
     * @param bundle 携带的bundle数据
     */
    void startNewActivity(@NonNull Class<?> clz, Bundle bundle);

    /**
     * 跳往新的Activity
     * @param clz 要跳转的Activity
     * @param bundle bundel数据
     * @param requestCode requestCode
     */
    void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode);
}
