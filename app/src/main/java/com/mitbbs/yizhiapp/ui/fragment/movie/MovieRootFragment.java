package com.mitbbs.yizhiapp.ui.fragment.movie;

import android.os.Bundle;
import android.view.View;

import com.mitbbs.sdk.base.fragment.BaseCompatFragment;
import com.mitbbs.yizhiapp.R;

/**
 * data:2017/12/28.
 *
 * @author:jc
 */
public class MovieRootFragment extends BaseCompatFragment {

    public static MovieRootFragment newInstance(){
        MovieRootFragment fragment = new MovieRootFragment();
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initUI(View mRootView, Bundle savedInstanceState) {

    }
}
