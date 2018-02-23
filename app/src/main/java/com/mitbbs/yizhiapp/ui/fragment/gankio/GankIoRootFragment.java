package com.mitbbs.yizhiapp.ui.fragment.gankio;

import android.os.Bundle;
import android.view.View;

import com.mitbbs.sdk.base.fragment.BaseCompatFragment;
import com.mitbbs.yizhiapp.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * data:2017/12/28.
 *
 * @author:jc
 */
public class GankIoRootFragment extends BaseCompatFragment{

    public static SupportFragment newInstance() {
        SupportFragment fragment = new SupportFragment();
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gookio;
    }

    @Override
    protected void initUI(View mRootView, Bundle savedInstanceState) {

    }


}
