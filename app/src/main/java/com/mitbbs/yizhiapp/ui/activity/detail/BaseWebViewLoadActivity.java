package com.mitbbs.yizhiapp.ui.activity.detail;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mitbbs.sdk.base.activity.BaseMVPCompatActivity;
import com.mitbbs.sdk.utils.AppUtils;
import com.mitbbs.sdk.utils.NetWorkConnectionUtils;
import com.mitbbs.sdk.widgets.NestScrollWebView;
import com.mitbbs.yizhiapp.R;
import com.mitbbs.yizhiapp.contract.detail.IBaseWebViewLoadContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * data:2018/2/2.
 *
 * @author:jc
 */
public abstract class BaseWebViewLoadActivity<P extends IBaseWebViewLoadContract.BaseWebViewLoadPresenter
        , M extends IBaseWebViewLoadContract.IBaseWebViewLoadMoudel> extends BaseMVPCompatActivity<P, M>
        implements IBaseWebViewLoadContract.IBaseWebViewLoadView {

    private final int endLoadHtml = 100;

    @BindView(R.id.iv_detail)
    ImageView ivDetail;
    @BindView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @BindView(R.id.tv_detail_copyright)
    TextView tvDetailCopyright;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.nswv_detail_content)
    NestScrollWebView nswvDetailContent;
    @BindView(R.id.fl_net_view)
    FrameLayout flNetView;
    @BindView(R.id.pb_web)
    ProgressBar pbWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTitleBar(toolbar,"跳转中...");
        initWebSetting(nswvDetailContent.getSettings());
        initWebView();

        loadDetail();
    }



    /**
     * js接口
     */
    public class SupportJavascriptInterface{
        private Context context;
        public SupportJavascriptInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void openImage(final String img){
            AppUtils.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    gotoImageBrowse(img);
                }
            });
        }
    }

    protected void initWebView() {
        // 添加js交互接口类，并起别名 imagelistner
        nswvDetailContent.addJavascriptInterface(new SupportJavascriptInterface(mContext),"imagelistner");
        nswvDetailContent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.getSettings().setJavaScriptEnabled(true);
                super.onPageFinished(view, url);
                // html加载完成之后，添加监听图片的点击js函数
                addWebImageClickListner(view);
                toolbar.setTitle(getToolbarTitle());
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                view.getSettings().setJavaScriptEnabled(true);
                super.onPageStarted(view, url, favicon);
            }

            // 注入js函数监听
            protected void addWebImageClickListner(WebView webView) {
                // 这段js函数的功能就是，遍历所有的img节点，并添加onclick函数，
                // 函数的功能是在图片点击的时候调用本地java接口并传递url过去
                webView.loadUrl("javascript:(function(){" +
                        "var objs = document.getElementsByTagName(\"img\"); " +
                        "for(var i=0;i<objs.length;i++)  " +
                        "{"
                        + "    objs[i].onclick=function()  " +
                        "    {  "
                        + "        window.imagelistner.openImage(this.src);  " +
                        "    }  " +
                        "}" +
                        "})()");
            }
        });

        nswvDetailContent.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == endLoadHtml){
                    pbWeb.setVisibility(View.GONE);
                }else {
                    pbWeb.setVisibility(View.VISIBLE);
                    pbWeb.setProgress(newProgress);
                }
            }
        });
    }




    /**
     * 初始化WebSetting
     *
     * @param settings WebSetting
     */
    protected void initWebSetting(WebSettings settings) {
        // 缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);
        // 保存表单数据
        settings.setSaveFormData(true);
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        settings.setSupportZoom(true);
        //        //是否支持手势缩放控制
        //        settings.setBuiltInZoomControls(true);
        //        是否隐藏原生的缩放控件
        //        settings.setDisplayZoomControls(false);
        // 启动应用缓存
        settings.setAppCacheEnabled(true);
        // 排版适应屏幕，只显示一列
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //  页面加载好以后，再放开图片
        settings.setBlockNetworkImage(false);
        // 使用localStorage则必须打开
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        // WebView启用JavaScript执行。默认的是false。
        // 设置支持javascript脚本
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (NetWorkConnectionUtils.isConnected(mContext)) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        }
    }
    @Override
    public void showNetworkError() {

    }

    @Override
    public void showPopupWindow() {

    }

    @Override
    public void dismissPopupWindow() {

    }

    @Override
    public boolean popupWindowIsShowing() {
        return false;
    }

    @Override
    public void gotoImageBrowse(String imgUrl) {

    }

    protected abstract void loadDetail();
    protected abstract String getToolbarTitle();
}
