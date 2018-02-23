package com.mitbbs.sdk.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mitbbs.sdk.AppManager;
import com.mitbbs.sdk.globle.GlobalApplication;
import com.mitbbs.sdk.utils.AppUtils;
import com.mitbbs.sdk.widgets.WaitPorgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * data:${DATA}.
 *
 * @author:jc
 */
public abstract class BaseCompatFragment extends SupportFragment{
    protected String TAG;
    protected Context mContext;
    protected Activity mActivity;
    protected GlobalApplication mApplication;
    protected WaitPorgressDialog mWaitPorgressDialog;
    protected Unbinder binder;
    protected View mRootView;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = inflater.inflate(getLayoutId(),null);
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            TAG = getClass().getSimpleName();
            binder = ButterKnife.bind(this,mRootView);
            getBundle(getArguments());
            initData();
            initUI(mRootView,savedInstanceState);
        }
        return mRootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binder != null){
            binder.unbind();
        }
    }

    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 得到Activity传进来的值
     */
    protected void getBundle(Bundle arguments) {

    }

    protected abstract void initUI(View mRootView, Bundle savedInstanceState);

    /**
     * 在监听器之前把数据准备好
     */
    protected void initData() {
        mWaitPorgressDialog = new WaitPorgressDialog(mActivity);
        mContext = AppUtils.getContext();
        mApplication = (GlobalApplication) mActivity.getApplication();
    }

    /**
     * 处理回退事件
     *
     * @return true 事件已消费
     * <p>
     * false 事件向上传递
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getFragmentManager().getBackStackEntryCount() > 1){
            //如果当前存在fragment>1，当前fragment出栈
            pop();
        }else {
            //已经退栈到root fragment，交由Activity处理回退事件
            return false;
        }
        return true;
    }

    /**
     * 显示提示框
     *
     * @param msg 提示框内容字符串
     */
    protected void showProgressDialog(String msg) {
        if (mWaitPorgressDialog.isShowing()) {
            mWaitPorgressDialog.dismiss();
        }

        mWaitPorgressDialog.setMessage(msg);
        mWaitPorgressDialog.show();
    }

    /**
     * 隐藏提示框
     */
    protected void hideProgressDialog() {
        if (mWaitPorgressDialog != null) {
            mWaitPorgressDialog.dismiss();
        }
    }
}
