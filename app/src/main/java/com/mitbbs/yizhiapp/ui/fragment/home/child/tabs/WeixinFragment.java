package com.mitbbs.yizhiapp.ui.fragment.home.child.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.mitbbs.sdk.base.BasePresenter;
import com.mitbbs.sdk.base.fragment.BaseRecycleFragment;
import com.mitbbs.yizhiapp.R;

/**
 * data:2017/12/29.
 *
 * @author:jc
 */
public class WeixinFragment extends BaseRecycleFragment {

    public static WeixinFragment newInstance() {
        Bundle args = new Bundle();
        WeixinFragment fragment = new WeixinFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_weixin;
    }

    @Override
    protected void initUI(View mRootView, Bundle savedInstanceState) {

    }

    @Override
    protected void onErrorViewClick(View v) {

    }

    @Override
    protected void showLoading() {

    }
}
