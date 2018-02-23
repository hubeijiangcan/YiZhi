package com.mitbbs.yizhiapp.model.home.tabs;

import com.mitbbs.sdk.base.BaseModel;
import com.mitbbs.sdk.helper.RetrofitCreateHelper;
import com.mitbbs.sdk.helper.RxHelper;
import com.mitbbs.yizhiapp.api.WangyiApi;
import com.mitbbs.yizhiapp.contract.home.tabs.IWangYiContract;
import com.mitbbs.yizhiapp.model.bean.wangyi.WangyiNewsListBean;

import io.reactivex.Observable;

/**
 * data:2018/1/16.
 *
 * @author:jc
 */
public class WangYiMoudel extends BaseModel implements IWangYiContract.IWangYiMoudel {
    public static WangYiMoudel newInstance(){
        return new WangYiMoudel();
    }
 

    @Override
    public Observable<WangyiNewsListBean> getNewsList(int id) {
        return RetrofitCreateHelper.createApi(WangyiApi.class,WangyiApi.HOST)
                .getNewsList(id).compose(RxHelper.<WangyiNewsListBean>rxSchedulerHelper());
    }

    @Override
    public Observable<Boolean> recordItemIsRead(String key) {
        return null;
    }
}
