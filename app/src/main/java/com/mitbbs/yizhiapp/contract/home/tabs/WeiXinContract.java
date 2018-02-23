package com.mitbbs.yizhiapp.contract.home.tabs;

import com.mitbbs.yizhiapp.model.bean.weixin.WeiXinChoiceItemBean;
import com.mitbbs.yizhiapp.model.bean.weixin.WeiXinChoiceListBean;
import com.mitbbs.yizhiapp.model.bean.zhihu.ZhihuDailyDetailBean;

import io.reactivex.Observable;

/**
 * date:2018/2/7.
 *
 * @author:jc
 */
public interface WeiXinContract {

    abstract class WeiXinPresent extends IBaseTabsContract.BaseTabsPresenter<IWeiXinMoudel,
            IWeiXinView,WeiXinChoiceItemBean>{
    }


    interface IWeiXinMoudel extends IBaseTabsContract.IBaseTabsModel{
        /**
         * 获取微信精选
         *
         * @param page      指定微信精选页数->空的话默认1
         * @param pageStrip 每页显示条数->空的话默认20条
         * @param dttype    返回数据的格式,xml或json，空的话->默认json
         * @param key       聚合key
         * @return Observable
         */
        Observable<WeiXinChoiceListBean> getWeixinChoiceList(int page,int pageStrip,String dttype,
                                                             String key);


    }

    interface IWeiXinView extends IBaseTabsContract.IBaseTabsView<WeiXinChoiceItemBean>{

    }
}
