package com.mitbbs.yizhiapp.presenter.home;

import android.support.annotation.NonNull;

import com.mitbbs.yizhiapp.contract.home.HomeMainContract;
import com.mitbbs.yizhiapp.model.home.HomeMainModel;

/**
 * data:2017/12/29.
 *
 * @author:jc
 */
public class HomeMainPresenter extends HomeMainContract.HomeMainPresenter {
    @NonNull
    public static HomeMainPresenter newInstance(){
        return new HomeMainPresenter();
    }
    @Override
    public void getTabList() {
        if (mIView == null || mIModel == null){
            return;
        }
        mIView.showTabList(mIModel.getTabs());
    }

    @Override
    public HomeMainContract.IHomeMainModel getModel() {
        return HomeMainModel.newInstance();
    }

    @Override
    protected void onStart() {

    }
}
