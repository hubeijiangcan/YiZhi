package com.mitbbs.yizhiapp.contract.home;

import com.mitbbs.sdk.base.BasePresenter;
import com.mitbbs.sdk.base.IBaseFragment;
import com.mitbbs.sdk.base.IBaseModel;
import com.mitbbs.sdk.base.IBaseView;

/**
 * data:2017/12/27.
 *主页Contract
 * @author:jc
 */
public interface HomeMainContract {
    //主页接口
    abstract class HomeMainPresenter extends BasePresenter<IHomeMainModel,IHomeMainView>{
        public abstract void getTabList();
    }

    interface IHomeMainModel extends IBaseModel{
        String[] getTabs();
    }

    interface IHomeMainView extends IBaseFragment{
        void showTabList(String[] tabs);
    }

}
