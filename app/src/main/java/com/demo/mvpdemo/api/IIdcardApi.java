package com.demo.mvpdemo.api;

import com.demo.mvpdemo.bean.response.BaseResult;
import com.demo.mvpdemo.bean.response.IdcardEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

/**
 * @projectName：MvpDemo
 * @createTime：2020/6/5 17:19
 * @author：chihuo
 * @company：
 * @e-mail：
 * @description：身份证查询接口
 */
public interface IIdcardApi {

    @GET("/idcard/query")
    Observable<BaseResult<IdcardEntity>> getIdcardInfo(@HeaderMap Map<String, Object> headerMap);
}
