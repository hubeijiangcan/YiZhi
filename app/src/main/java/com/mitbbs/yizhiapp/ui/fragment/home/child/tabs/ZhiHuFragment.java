package com.mitbbs.yizhiapp.ui.fragment.home.child.tabs;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mitbbs.sdk.base.BasePresenter;
import com.mitbbs.sdk.base.fragment.BaseRecycleFragment;
import com.mitbbs.yizhiapp.R;
import com.mitbbs.yizhiapp.adapter.ZhihuAdapter;
import com.mitbbs.yizhiapp.constant.BundleKeyConstant;
import com.mitbbs.yizhiapp.contract.home.tabs.IZhiHuContract;
import com.mitbbs.yizhiapp.model.bean.zhihu.ZhihuDailyItemBean;
import com.mitbbs.yizhiapp.presenter.home.tabs.ZhihuPresenter;
import com.mitbbs.yizhiapp.ui.activity.detail.ZhihuDailyDetailActivity;

import java.util.List;

import butterknife.BindView;

/**
 * data:2017/12/29.
 *
 * @author:jc
 */
public class ZhiHuFragment extends BaseRecycleFragment<IZhiHuContract.ZhiHuPresenter,
        IZhiHuContract.IZhiHuModel> implements IZhiHuContract.IZhiHuView,
        BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.rv_zhihu)
    RecyclerView rvZhihu;
    private ZhihuAdapter mZhihuAdapter;
    public static ZhiHuFragment newInstance() {
        Bundle args = new Bundle();
        ZhiHuFragment fragment = new ZhiHuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return ZhihuPresenter.newInstance();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_zhihu;
    }

    @Override
    protected void initUI(View mRootView, Bundle savedInstanceState) {
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        mZhihuAdapter = new ZhihuAdapter(R.layout.item_recycle_home);
        rvZhihu.setAdapter(mZhihuAdapter);
        rvZhihu.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //第一次显示时请求最新的list
        mPresenter.loadLatestList();
    }



    @Override
    public void updateContentList(List<ZhihuDailyItemBean> list) {
        if (mZhihuAdapter.getData().size() == 0){
            initRecycleView(list);
        }else {
            mZhihuAdapter.addData(list);
        }
    }

    private void initRecycleView(List<ZhihuDailyItemBean> list) {
        mZhihuAdapter = new ZhihuAdapter(R.layout.item_recycle_home, list);
        mZhihuAdapter.setOnLoadMoreListener(this,rvZhihu);
        mZhihuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.onItemClick(position, (ZhihuDailyItemBean) adapter.getItem(position));
            }
        });
        rvZhihu.setAdapter(mZhihuAdapter);
    }

    @Override
    public void itemNotifyChanged(int position) {
        mZhihuAdapter.notifyItemChanged(position);
    }

    @Override
    public void showNetworkError() {

    }
    @Override
    protected void showLoading() {
        mZhihuAdapter.setEmptyView(loadingView);
    }
    @Override
    public void showLoadMoreError() {
        mZhihuAdapter.loadMoreFail();
    }

    @Override
    public void showNoMoreData() {

    }

    @Override
    public void onLoadMoreRequested() {
        Log.e(TAG,"onLoadMoreRequested");
        mZhihuAdapter.loadMoreComplete();
        mPresenter.loadMoreList();
    }

    @Override
    protected void onErrorViewClick(View v) {

    }
}
