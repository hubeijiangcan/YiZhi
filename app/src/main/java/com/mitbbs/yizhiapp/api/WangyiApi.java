package com.mitbbs.yizhiapp.api;

import com.mitbbs.yizhiapp.model.bean.wangyi.WangyiNewsListBean;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * data:2018/2/2.
 *
 * @author:jc
 */
public interface WangyiApi {
    public final String HOST = "http://c.m.163.com";

    @GET("/nc/article/headline/T1348647909107/{id}-20.html")
    Observable<WangyiNewsListBean> getNewsList(@Path("id") int id);

    @GET("/nc/article/{id}/full.html")
    Observable<ResponseBody> getNewsDetail(@Path("id") String id);
}
