package com.mitbbs.sdk.helper;

import com.mitbbs.sdk.helper.okhttp.CacheIntercptor;
import com.mitbbs.sdk.helper.okhttp.HttpCache;
import com.mitbbs.sdk.helper.okhttp.TrustManager;


import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * data:2018/1/3.
 *
 * @author:jc
 */
public class RetrofitCreateHelper {
    private static final int TIMEOUT_READ = 30;
    private static final int TIMEOUT_CONNECTION = 10;
    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    private static CacheIntercptor cacheInterceptor = new CacheIntercptor();
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            //SSL证书
            .sslSocketFactory(TrustManager.getUnsafeOkHttpClient())
            .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
            //打印日志
            .addInterceptor(interceptor)
            //设置Cache拦截器
            .addNetworkInterceptor(cacheInterceptor)
            .addInterceptor(cacheInterceptor)
            .cache(HttpCache.getCache())
            //time out
            .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ,TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_READ,TimeUnit.SECONDS)
            //失败重连
            .retryOnConnectionFailure(true)
            .build();

    public static <T> T createApi(Class<T> clazz,String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);

    }

}
