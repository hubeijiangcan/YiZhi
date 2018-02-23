package com.mitbbs.yizhiapp.contract.home.tabs;

import com.mitbbs.yizhiapp.model.bean.zhihu.ZhihuDailyDetailBean;
import com.mitbbs.yizhiapp.model.bean.zhihu.ZhihuDailyItemBean;
import com.mitbbs.yizhiapp.model.bean.zhihu.ZhihuDailyListBean;

import io.reactivex.Observable;

/**
 * data:2018/1/2.
 *
 * @author:jc
 */
public interface IZhiHuContract {

    abstract class ZhiHuPresenter extends IBaseTabsContract.BaseTabsPresenter<IZhiHuModel,
            IZhiHuView,ZhihuDailyItemBean>{

    }
    interface IZhiHuModel extends IBaseTabsContract.IBaseTabsModel{
        /**
         * 根据日期获取日报list --> 20170910
         *
         * @param date 日期
         * @return Observable
         */
        Observable<ZhihuDailyListBean> getDailyList(String date);

        /**
         * 获取日报list
         *
         * @return Observable
         */
        Observable<ZhihuDailyListBean> getDailyList();
    }

    interface IZhiHuView extends IBaseTabsContract.IBaseTabsView<ZhihuDailyItemBean>{

    }
}
