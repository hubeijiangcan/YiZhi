package com.mitbbs.yizhiapp.presenter.home.tabs;

import com.mitbbs.yizhiapp.contract.home.tabs.IWangYiContract;
import com.mitbbs.yizhiapp.model.bean.wangyi.WangyiNewsItemBean;
import com.mitbbs.yizhiapp.model.bean.wangyi.WangyiNewsListBean;
import com.mitbbs.yizhiapp.model.home.tabs.WangYiMoudel;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * data:2018/1/9.
 *
 * @author:jc
 */
public class WangYiPresenter extends IWangYiContract.WangYiPresenter{
    private int mCurrentIndex;
    private boolean isLoading;

    public static WangYiPresenter newInstance(){
        return new WangYiPresenter();
    }

    @Override
    public IWangYiContract.IWangYiMoudel getModel() {
        return WangYiMoudel.newInstance();
    }

    @Override
    protected void onStart() {

    }

    @Override
    public void loadLatestList() {
        mCurrentIndex = 0;
        mRxManager.register(mIModel.getNewsList(mCurrentIndex).subscribe(new Consumer<WangyiNewsListBean>() {
            @Override
            public void accept(WangyiNewsListBean wangyiNewsListBean) throws Exception {
                if (mIView != null) {
                    List<WangyiNewsItemBean> list = wangyiNewsListBean.getNewsList();

                    mCurrentIndex += 20;
                    mIView.updateContentList(list);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView != null){
                    if (mIView.isVisiable()){
                        mIView.showToast("Network error.");
                        mIView.showNetworkError();
                    }
                }
            }
        }));
    }

    @Override
    public void loadMoreList() {

    }

    @Override
    public void onItemClick(int position, WangyiNewsItemBean item) {

    }
}
