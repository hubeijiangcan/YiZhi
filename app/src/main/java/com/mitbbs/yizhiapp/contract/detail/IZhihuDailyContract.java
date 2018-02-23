package com.mitbbs.yizhiapp.contract.detail;

import com.mitbbs.yizhiapp.model.bean.zhihu.ZhihuDailyDetailBean;

import io.reactivex.Observable;

/**
 * data:2018/2/6.
 *
 * @author:jc
 */
public interface IZhihuDailyContract {

    abstract class ZhihuDetailPresent
            extends IBaseWebViewLoadContract.BaseWebViewLoadPresenter<IZhihuDetailMoudel,IZhihuDetailView>{
        /**
         * 加载日报详情
         */
        public abstract void loadDailyDetail(String id);
    }

    interface IZhihuDetailMoudel extends IBaseWebViewLoadContract.IBaseWebViewLoadMoudel{
        /**
         * 获取日报详情
         *
         * @param id 日报id
         * @return Observable
         */
        Observable<ZhihuDailyDetailBean> getDailyDetail(String id);
    }

    interface IZhihuDetailView extends IBaseWebViewLoadContract.IBaseWebViewLoadView{
        /**
         * 显示日报详细内容
         *
         * @param bean ZhihuDailyDetailBean
         */
        void showDailyDetail(ZhihuDailyDetailBean bean);
    }
}
