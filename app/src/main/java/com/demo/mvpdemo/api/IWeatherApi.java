package com.demo.mvpdemo.api;

import com.demo.mvpdemo.bean.response.WeatherEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface IWeatherApi {

    @GET("/api/")
    Observable<WeatherEntity> getWeatherInfo(@HeaderMap Map<String, Object> headerMap);
}
