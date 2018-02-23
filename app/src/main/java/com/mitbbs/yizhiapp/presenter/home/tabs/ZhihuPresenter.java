package com.mitbbs.yizhiapp.presenter.home.tabs;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mitbbs.yizhiapp.constant.BundleKeyConstant;
import com.mitbbs.yizhiapp.contract.home.tabs.IZhiHuContract;
import com.mitbbs.yizhiapp.model.bean.zhihu.ZhihuDailyItemBean;
import com.mitbbs.yizhiapp.model.bean.zhihu.ZhihuDailyListBean;
import com.mitbbs.yizhiapp.model.home.tabs.ZhihuModel;
import com.mitbbs.yizhiapp.ui.activity.detail.ZhihuDailyDetailActivity;

import io.reactivex.functions.Consumer;

/**
 * data:2018/1/2.
 *
 * @author:jc
 */
public class ZhihuPresenter extends IZhiHuContract.ZhiHuPresenter {
    private String mDate;

    public static ZhihuPresenter newInstance() {
        return new ZhihuPresenter();
    }


    @Override
    public void loadLatestList() {
        if (mIModel == null) {
            return;
        }
        mRxManager.register(mIModel.getDailyList().subscribe(new Consumer<ZhihuDailyListBean>() {
            @Override
            public void accept(ZhihuDailyListBean zhihuDailyListBean) throws Exception {
                mDate = zhihuDailyListBean.getDate();
                if (mIView != null){
                    mIView.updateContentList(zhihuDailyListBean.getStories());
                }
            }
        }));

    }

    @Override
    public void loadMoreList() {
        if (mIModel == null){
            return;
        }
        mRxManager.register(mIModel.getDailyList(mDate).subscribe(new Consumer<ZhihuDailyListBean>() {
            @Override
            public void accept(ZhihuDailyListBean zhihuDailyListBean) throws Exception {
                if (mDate.equals(zhihuDailyListBean.getDate())) {
                    return;
                }
                mDate = zhihuDailyListBean.getDate();
                if (mIView != null) {
                    mIView.updateContentList(zhihuDailyListBean.getStories());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView != null) {
                    mIView.showLoadMoreError();
                }
            }
        }));
    }

    @Override
    public void onItemClick(int position, ZhihuDailyItemBean item) {
        if (mIView == null){
            return;
        }
        Log.e("Tag","onItemClick1");
        Bundle bundle = new Bundle();
        bundle.putString(BundleKeyConstant.ARG_KEY_ZHIHU_DETAIL_ID, item.getId());
        bundle.putString(BundleKeyConstant.ARG_KEY_ZHIHU_DETAIL_TITLE, item.getTitle());
        mIView.startNewActivity(ZhihuDailyDetailActivity.class, bundle);
    }


    @Override
    public IZhiHuContract.IZhiHuModel getModel() {
        return ZhihuModel.newInstance();
    }

    @Override
    protected void onStart() {

    }
}
