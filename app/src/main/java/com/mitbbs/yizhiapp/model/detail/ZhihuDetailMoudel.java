package com.mitbbs.yizhiapp.model.detail;

import com.mitbbs.sdk.base.BaseModel;
import com.mitbbs.sdk.helper.RetrofitCreateHelper;
import com.mitbbs.sdk.helper.RxHelper;
import com.mitbbs.yizhiapp.api.ZhihuApi;
import com.mitbbs.yizhiapp.contract.detail.IZhihuDailyContract;
import com.mitbbs.yizhiapp.model.bean.zhihu.ZhihuDailyDetailBean;

import io.reactivex.Observable;

/**
 * data:2018/2/6.
 *
 * @author:jc
 */
public class ZhihuDetailMoudel extends BaseModel implements IZhihuDailyContract.IZhihuDetailMoudel {

    public static ZhihuDetailMoudel newInstance(){
        return new ZhihuDetailMoudel();
    }
    @Override
    public Observable<ZhihuDailyDetailBean> getDailyDetail(String id) {
        return RetrofitCreateHelper.createApi(ZhihuApi.class,ZhihuApi.HOST).getZhihuDailyDetail(id)
                .compose(RxHelper.<ZhihuDailyDetailBean>rxSchedulerHelper());
    }
}
