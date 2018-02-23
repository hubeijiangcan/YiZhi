package com.mitbbs.yizhiapp.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mitbbs.yizhiapp.R;
import com.mitbbs.yizhiapp.model.bean.zhihu.ZhihuDailyItemBean;

import java.util.List;

/**
 * data:2018/1/2.
 *
 * @author:jc
 */
public class ZhihuAdapter extends BaseCompatAdapter<ZhihuDailyItemBean, BaseViewHolder> {
    public ZhihuAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public ZhihuAdapter(@LayoutRes int layoutResId, @Nullable List<ZhihuDailyItemBean> data) {
        super(layoutResId, data);
    }
    public ZhihuAdapter(@Nullable List<ZhihuDailyItemBean> data) {
        super(data);
    }
    @Override
    protected void convert(BaseViewHolder helper, ZhihuDailyItemBean item) {
        helper.setText(R.id.tv_item_title, item.getTitle());
        Glide.with(mContext).load(item.getImages()[0]).crossFade().into((ImageView) helper.getView(R
                .id.iv_item_image));
    }
}
