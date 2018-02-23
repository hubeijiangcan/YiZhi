package com.mitbbs.sdk;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * data:2017/12/26.
 *
 * @author:jc
 * <p>
 *     用于管理Rxjava 注册订阅和取消订阅
 * </p>
 */
public class RxManager {
    /** 管理订阅者者*/
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public void register(Disposable d){
        mCompositeDisposable.add(d);
    }

    public void unSubscribe(){
        mCompositeDisposable.dispose();
    }

}
