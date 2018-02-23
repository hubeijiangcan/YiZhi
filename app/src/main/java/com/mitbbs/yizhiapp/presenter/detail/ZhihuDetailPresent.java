package com.mitbbs.yizhiapp.presenter.detail;

import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;

import com.mitbbs.yizhiapp.contract.detail.IZhihuDailyContract;
import com.mitbbs.yizhiapp.model.bean.zhihu.ZhihuDailyDetailBean;
import com.mitbbs.yizhiapp.model.detail.ZhihuDetailMoudel;

import io.reactivex.functions.Consumer;

/**
 * data:2018/2/6.
 *
 * @author:jc
 */
public class ZhihuDetailPresent extends IZhihuDailyContract.ZhihuDetailPresent {

    public static ZhihuDetailPresent newInstance(){
        return new ZhihuDetailPresent();
    }
    @Override
    public void loadDailyDetail(String id) {
        if (mIModel == null){
            return;
        }
        mRxManager.register(mIModel.getDailyDetail(id).subscribe(new Consumer<ZhihuDailyDetailBean>() {
            @Override
            public void accept(ZhihuDailyDetailBean zhihuDailyDetailBean) throws Exception {
                if (mIView != null){
                    mIView.showDailyDetail(zhihuDailyDetailBean);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView != null) {
                    mIView.showToast("网络异常");
                    mIView.showNetworkError();
                }
            }
        }));
    }

    @Override
    public IZhihuDailyContract.IZhihuDetailMoudel getModel() {
        return ZhihuDetailMoudel.newInstance();
    }

    @Override
    protected void onStart() {

    }

    @Override
    public void saveImageClicked(FragmentActivity activity, String imgUrl) {

    }

    @Override
    public void gotoImageBrowseClicked(String imgUrl) {

    }

    @Override
    public void imageLongClicked(WebView.HitTestResult hitTestResult) {

    }
}
