package com.mitbbs.yizhiapp.ui.fragment.personal;

import android.os.Bundle;
import android.view.View;

import com.mitbbs.sdk.base.fragment.BaseCompatFragment;
import com.mitbbs.yizhiapp.R;

/**
 * data:2017/12/28.
 *
 * @author:jc
 */
public class PersonalRootFragment extends BaseCompatFragment{
    public static PersonalRootFragment newInstance(){
        PersonalRootFragment fragment = new PersonalRootFragment();
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initUI(View mRootView, Bundle savedInstanceState) {

    }
}
