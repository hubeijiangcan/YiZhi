package com.mitbbs.yizhiapp.model.home.tabs;

import com.mitbbs.sdk.base.BaseModel;
import com.mitbbs.sdk.helper.RetrofitCreateHelper;
import com.mitbbs.sdk.helper.RxHelper;
import com.mitbbs.yizhiapp.api.ZhihuApi;
import com.mitbbs.yizhiapp.contract.home.tabs.IZhiHuContract;
import com.mitbbs.yizhiapp.model.bean.zhihu.ZhihuDailyListBean;

import io.reactivex.Observable;

/**
 * data:2018/1/2.
 *
 * @author:jc
 */
public class ZhihuModel extends BaseModel implements IZhiHuContract.IZhiHuModel{
    public static ZhihuModel newInstance(){
        return new ZhihuModel();
    }
    @Override
    public Observable<ZhihuDailyListBean> getDailyList(String date) {

        return RetrofitCreateHelper.createApi(ZhihuApi.class,ZhihuApi.HOST).getDailyListWithDate(date)
                .compose(RxHelper.<ZhihuDailyListBean>rxSchedulerHelper());
    }

    @Override
    public Observable<ZhihuDailyListBean> getDailyList() {
        return RetrofitCreateHelper.createApi(ZhihuApi.class, ZhihuApi.HOST).getLastDailyList()
                .compose(RxHelper.<ZhihuDailyListBean>rxSchedulerHelper());
    }

    @Override
    public Observable<Boolean> recordItemIsRead(String key) {
        return null;
    }
}
