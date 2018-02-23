package com.mitbbs.yizhiapp.model.bean.wangyi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * data:2018/1/9.
 *
 * @author:jc
 */
public class WangyiNewsListBean {
    @SerializedName("T1348647909107")
    ArrayList<WangyiNewsItemBean> newsList;

    public ArrayList<WangyiNewsItemBean> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<WangyiNewsItemBean> newsList) {
        this.newsList = newsList;
    }
}
