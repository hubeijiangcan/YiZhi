package com.mitbbs.yizhiapp.model.home;

import com.mitbbs.sdk.base.BaseModel;
import com.mitbbs.yizhiapp.contract.home.HomeMainContract;

/**
 * data:2017/12/29.
 *
 * @author:jc
 */
public class HomeMainModel extends BaseModel implements HomeMainContract.IHomeMainModel {

    public static HomeMainModel newInstance(){
        return new HomeMainModel();
    }
    @Override
    public String[] getTabs() {
        return new String[]{"知乎日报", "热点新闻", "微信精选"};
    }
}
