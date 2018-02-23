package com.mitbbs.yizhiapp.ui.activity.detail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.bumptech.glide.Glide;
import com.mitbbs.sdk.base.BasePresenter;
import com.mitbbs.sdk.utils.HtmlUtils;
import com.mitbbs.yizhiapp.constant.BundleKeyConstant;
import com.mitbbs.yizhiapp.contract.detail.IZhihuDailyContract;
import com.mitbbs.yizhiapp.model.bean.zhihu.ZhihuDailyDetailBean;
import com.mitbbs.yizhiapp.presenter.detail.ZhihuDetailPresent;

/**
 * data:2018/2/2.
 *
 * @author:jc
 */
public class ZhihuDailyDetailActivity extends BaseWebViewLoadActivity<IZhihuDailyContract.ZhihuDetailPresent,
        IZhihuDailyContract.IZhihuDetailMoudel> implements IZhihuDailyContract.IZhihuDetailView{

    private String mId, mTitle;

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            mId = bundle.getString(BundleKeyConstant.ARG_KEY_ZHIHU_DETAIL_ID);
            mTitle = bundle.getString(BundleKeyConstant.ARG_KEY_ZHIHU_DETAIL_TITLE);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvDetailTitle.setText(mTitle);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return ZhihuDetailPresent.newInstance();
    }

    @Override
    public void showDailyDetail(ZhihuDailyDetailBean bean) {
        flNetView.setVisibility(View.GONE);
        Glide.with(mContext).load(bean.getImage()).crossFade().into(ivDetail);
        tvDetailTitle.setText(bean.getTitle());
        tvDetailCopyright.setText(bean.getImage_source());
        String htmlData = HtmlUtils.createHtmlData(bean.getBody(), bean.getCss(), bean.getJs());
        nswvDetailContent.loadData(htmlData, HtmlUtils.MIME_TYPE, HtmlUtils.ENCODING);
    }

    @Override
    protected void loadDetail() {
        if (mPresenter != null){
            mPresenter.loadDailyDetail(mId);
        }
    }

    @Override
    protected String getToolbarTitle() {
        return "知乎日报";
    }
}
