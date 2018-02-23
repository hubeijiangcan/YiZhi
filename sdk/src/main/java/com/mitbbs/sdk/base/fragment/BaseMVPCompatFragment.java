package com.mitbbs.sdk.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.mitbbs.sdk.base.BasePresenter;
import com.mitbbs.sdk.base.IBaseFragment;
import com.mitbbs.sdk.base.IBaseModel;
import com.mitbbs.sdk.base.activity.BaseCompatActivity;
import com.mitbbs.sdk.utils.ToastUtils;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * data:2017/12/26.
 *
 * @author:jc
 */
public abstract class BaseMVPCompatFragment<P extends BasePresenter,M extends IBaseModel> extends
        BaseCompatFragment implements IBaseFragment {
    public P mPresenter;
    public M mIMode;


    /**
     * 在监听器之前把数据准备好
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
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null){
            mPresenter.detachMV();
        }
    }

    @Override
    public void showWaitDialog(String waitMsg) {
        showProgressDialog(waitMsg);
    }

    @Override
    public void hideWaitDialog() {
        hideProgressDialog();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(mContext, msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void back() {
        this.onBackPressedSupport();
    }

    @Override
    public void startNewFragment(@NonNull SupportFragment supportFragment) {
        start(supportFragment);
    }

    @Override
    public void startNewFragmentWithPop(@NonNull SupportFragment supportFragment) {
        startWithPop(supportFragment);
    }

    @Override
    public void startNewFragmentForResult(@NonNull SupportFragment supportFragment, int
            requestCode) {
        startForResult(supportFragment, requestCode);
    }

    @Override
    public void popToFragment(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        popTo(targetFragmentClass, includeTargetFragment);
    }

    @Override
    public void hideKeybord() {
        hideSoftInput();
    }

    @Override
    public void setOnFragmentResult(int ResultCode, Bundle data) {
        setFragmentResult(ResultCode, data);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz) {
        ((BaseCompatActivity) mActivity).startActivity(clz);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
        ((BaseCompatActivity) mActivity).startActivity(clz, bundle);
    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
        ((BaseCompatActivity) mActivity).startActivityForResult(clz, bundle, requestCode);
    }

    @Override
    public boolean isVisiable() {
        return isSupportVisible();
    }

    @Override
    public Activity getBindActivity() {
        return mActivity;
    }
}
