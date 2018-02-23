package com.mitbbs.yizhiapp.ui.fragment.home;

import android.os.Bundle;
import android.view.View;

import com.mitbbs.sdk.base.fragment.BaseCompatFragment;
import com.mitbbs.yizhiapp.R;
import com.mitbbs.yizhiapp.ui.fragment.home.child.HomeFragment;

/**
 * data:2017/12/28.
 *
 * @author:jc
 */
public class HomeRootFragment extends BaseCompatFragment {
    public static HomeRootFragment newInstance() {
        Bundle args = new Bundle();
        HomeRootFragment fragment = new HomeRootFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initUI(View mRootView, Bundle savedInstanceState) {
        if (findChildFragment(HomeFragment.class) == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        }
    }
}
