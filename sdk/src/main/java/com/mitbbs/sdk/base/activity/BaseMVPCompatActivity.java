package com.mitbbs.sdk.base.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.mitbbs.sdk.base.BasePresenter;
import com.mitbbs.sdk.base.IBaseActivity;
import com.mitbbs.sdk.base.IBaseModel;
import com.mitbbs.sdk.utils.ToastUtils;

/**
 * data:2018/2/2.
 *
 * @author:jc
 */
public abstract class BaseMVPCompatActivity<P extends BasePresenter, M extends IBaseModel>
        extends BaseCompatActivity implements IBaseActivity {
    /**
     * presenter 具体的presenter由子类确定
     */
    protected P mPresenter;

    /**
     * model 具体的model由子类确定
     */
    private M mIMode;

    /**
     * 初始化数据
     * <p>
     * 子类可以复写此方法初始化子类数据
     */

    @Override
    protected void initData() {
        super.initData();
        mPresenter = (P) initPresenter();

        if (mPresenter != null){
            mIMode = (M) mPresenter.getModel();
            if (mIMode != null){
                mPresenter.attachMV(mIMode,this);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
            //Logger.d("detach M V success.");
        }
    }

    @Override
    public void showWaitDialog(String msg) {
        showProgressDialog(msg);
    }

    @Override
    public void hideWaitDialog() {
        hideProgressDialog();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz) {
        startActivity(clz);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
        startActivity(clz, bundle);
    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
        startActivityForResult(clz, bundle, requestCode);
    }

    @Override
    public void hideKeybord() {
        hiddenKeyboard();
    }

    @Override
    public void back() {
        super.onBackPressedSupport();
    }
}
