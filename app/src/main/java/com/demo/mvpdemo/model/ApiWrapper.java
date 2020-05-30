package com.demo.mvpdemo.model;


import androidx.annotation.NonNull;

import com.demo.mvpdemo.BuildConfig;
import com.demo.mvpdemo.Constants;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiWrapper {

    private final static int CONNECT_TIME_OUT = 30; // 连接超时
    private final static int REQUEST_TIME_OUT = 30; // 请求超时
    private final static int RESPONSE_TIME_OUT = 30; // 响应超时
    private static String BASE_URL = Constants.BASE_URL;

    private volatile static ApiWrapper instance;
    private Retrofit retrofit;
    private static boolean isCustomTime = false;//是否自定义时间


    private ApiWrapper() {
        this(CONNECT_TIME_OUT, REQUEST_TIME_OUT, RESPONSE_TIME_OUT);
    }

    private ApiWrapper(String url) {
        this(url, CONNECT_TIME_OUT, REQUEST_TIME_OUT, RESPONSE_TIME_OUT);
    }

    private ApiWrapper(int timeout_c, int timeout_r, int timeout_w) {
        this(BASE_URL, timeout_c, timeout_r, timeout_w);
    }

    private ApiWrapper(String baseUrl, int timeout_c, int timeout_r, int timeout_w) {
        Constants.BASE_URL = BASE_URL = baseUrl;
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(timeout_c, TimeUnit.SECONDS)
                .readTimeout(timeout_r, TimeUnit.SECONDS)
                .writeTimeout(timeout_w, TimeUnit.SECONDS);
        // 只有 debug 版本添加 log 信息打印
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addInterceptor(interceptor);
        }
        retrofit = getRetrofit(okHttpClient, getUrl(baseUrl));
    }

    /**
     * 添加默认地址
     *
     * @param url
     * @return
     */
    public String getUrl(String url) {
        if (!url.contains("http")) {
            url = "http://" + url;
        }
        return url;
    }

    @NonNull
    private Retrofit getRetrofit(OkHttpClient.Builder okHttpClient, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient.build())
                .build();
    }


    public static ApiWrapper getInstance() {
        synchronized (ApiWrapper.class) {
            if (instance == null) {
                instance = new ApiWrapper();
            } else {
                if (isCustomTime) {
                    isCustomTime = false;
                    instance = new ApiWrapper();
                }
            }
        }
        return instance;
    }

    public static ApiWrapper getInstance(String url) {
        synchronized (ApiWrapper.class) {
            instance = new ApiWrapper(url);
        }
        return instance;
    }

    public static ApiWrapper getInstance(int timeout_c, int timeout_r, int timeout_w) {
        synchronized (ApiWrapper.class) {
            isCustomTime = true;
            instance = new ApiWrapper(timeout_c, timeout_r, timeout_w);
        }
        return instance;
    }

    public static ApiWrapper getInstance(String url, int timeout_c, int timeout_r, int timeout_w) {
        synchronized (ApiWrapper.class) {
            isCustomTime = true;
            instance = new ApiWrapper(url, timeout_c, timeout_r, timeout_w);
        }
        return instance;
    }

    public <T> T create(Class<T> services) {
        return retrofit.create(services);
    }

}