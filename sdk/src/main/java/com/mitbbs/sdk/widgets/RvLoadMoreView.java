package com.mitbbs.sdk.widgets;

import com.chad.library.adapter.base.loadmore.LoadMoreView;

/**
 * data:2018/1/2.
 *
 * @author:jc
 */
public class RvLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected int getLoadingViewId() {
        return 0;
    }

    @Override
    protected int getLoadFailViewId() {
        return 0;
    }

    @Override
    protected int getLoadEndViewId() {
        return 0;
    }
}
