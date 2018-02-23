package com.mitbbs.yizhiapp.ui.fragment.book;

import android.os.Bundle;
import android.view.View;

import com.mitbbs.sdk.base.fragment.BaseCompatFragment;
import com.mitbbs.yizhiapp.R;

/**
 * data:2017/12/28.
 *
 * @author:jc
 */
public class BookRootFragment extends BaseCompatFragment{

    public static BookRootFragment newInstance(){
        BookRootFragment fragment = new BookRootFragment();
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_book;
    }

    @Override
    protected void initUI(View mRootView, Bundle savedInstanceState) {

    }
}
