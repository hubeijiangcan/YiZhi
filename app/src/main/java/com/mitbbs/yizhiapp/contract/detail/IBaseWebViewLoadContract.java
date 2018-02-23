package com.mitbbs.yizhiapp.contract.detail;

import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;

import com.mitbbs.sdk.base.BasePresenter;
import com.mitbbs.sdk.base.IBaseActivity;
import com.mitbbs.sdk.base.IBaseModel;

/**
 * data:2018/2/2.
 *
 * @author:jc
 */
public interface IBaseWebViewLoadContract {

    abstract class BaseWebViewLoadPresenter<M extends IBaseWebViewLoadMoudel,
            V extends IBaseWebViewLoadView> extends BasePresenter<M,V>{
        /**
         * 保存图片点击
         *
         * @param activity {@link FragmentActivity}
         * @param imgUrl   imgUrl
         */
        public abstract void saveImageClicked(FragmentActivity activity, String imgUrl);

        /**
         * 跳转图片详情按钮点击
         *
         * @param imgUrl imgUrl
         */
        public abstract void gotoImageBrowseClicked(String imgUrl);

        /**
         * 图片长按事件
         *
         * @param hitTestResult {@link WebView.HitTestResult}
         */
        public abstract void imageLongClicked(WebView.HitTestResult hitTestResult);
    }

    interface IBaseWebViewLoadMoudel extends IBaseModel{

    }

    interface IBaseWebViewLoadView extends IBaseActivity{
        /**
         * 显示网络错误
         */
        void showNetworkError();

        /**
         * 显示popupWindow
         */
        void showPopupWindow();

        /**
         * 隐藏popupWindow
         */
        void dismissPopupWindow();

        /**
         * 返回popup显示状态
         *
         * @return popup显示状态
         */
        boolean popupWindowIsShowing();

        /**
         * 跳转到图片详情页
         *
         * @param imgUrl 图片url
         */
        void gotoImageBrowse(String imgUrl);
    }
}
