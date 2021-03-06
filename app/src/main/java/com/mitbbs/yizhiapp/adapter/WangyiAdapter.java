package com.mitbbs.yizhiapp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mitbbs.yizhiapp.R;
import com.mitbbs.yizhiapp.model.bean.wangyi.WangyiNewsItemBean;

import java.util.List;

/**
 * data:2018/2/2.
 *
 * @author:jc
 */
public class WangyiAdapter extends BaseCompatAdapter<WangyiNewsItemBean,BaseViewHolder>{
    public WangyiAdapter(@LayoutRes int layoutResId, @Nullable List<WangyiNewsItemBean> data) {
        super(layoutResId, data);
    }

    public WangyiAdapter(@Nullable List<WangyiNewsItemBean> data) {
        super(data);
    }

    public WangyiAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, WangyiNewsItemBean item) {
        helper.setText(R.id.tv_item_title, item.getTitle());
        helper.setText(R.id.tv_item_who, item.getSource());
        helper.setText(R.id.tv_item_time, item.getPtime());
        Glide.with(mContext).load(item.getImgsrc()).crossFade().into((ImageView) helper.getView(R
                .id.iv_item_image));
    }
}
