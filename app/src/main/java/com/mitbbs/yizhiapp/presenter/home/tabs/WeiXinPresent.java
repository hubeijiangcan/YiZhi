package com.mitbbs.yizhiapp.presenter.home.tabs;

import com.mitbbs.sdk.RxManager;
import com.mitbbs.yizhiapp.contract.home.tabs.WeiXinContract;
import com.mitbbs.yizhiapp.model.bean.weixin.WeiXinChoiceItemBean;

/**
 * date:2018/2/7.
 *
 * @author:jc
 */
public class WeiXinPresent extends WeiXinContract.WeiXinPresent {

    public static WeiXinPresent getInstance(){
        return new WeiXinPresent();
    }
    @Override
    public WeiXinContract.IWeiXinMoudel getModel() {
        return null;
    }

    @Override
    protected void onStart() {

    }

    @Override
    public void loadLatestList() {

    }

    @Override
    public void loadMoreList() {

    }

    @Override
    public void onItemClick(int position, WeiXinChoiceItemBean item) {

    }
}
