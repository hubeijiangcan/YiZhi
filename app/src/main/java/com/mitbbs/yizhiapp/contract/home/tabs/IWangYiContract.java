package com.mitbbs.yizhiapp.contract.home.tabs;

import com.mitbbs.yizhiapp.model.bean.wangyi.WangyiNewsItemBean;
import com.mitbbs.yizhiapp.model.bean.wangyi.WangyiNewsListBean;

import io.reactivex.Observable;


/**
 * data:2018/1/8.
 *
 * @author:jc
 */
public interface IWangYiContract {

    abstract class WangYiPresenter extends IBaseTabsContract.BaseTabsPresenter<IWangYiMoudel,IWangYiView,WangyiNewsItemBean>{

    }

    interface IWangYiMoudel extends IBaseTabsContract.IBaseTabsModel{
        /**
         * 获取网易新闻list
         *
         * @param id id
         * @return Observable
         */
        Observable<WangyiNewsListBean> getNewsList(int id);
    }
    interface IWangYiView extends IBaseTabsContract.IBaseTabsView<WangyiNewsItemBean>{

    }
}
