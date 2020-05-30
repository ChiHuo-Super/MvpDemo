package com.demo.mvpdemo.model;

import com.demo.mvpdemo.api.IWeatherApi;
import com.demo.mvpdemo.bean.response.WeatherEntity;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherLoader {

    /**
     * 获取指定地区天气数据
     *
     * @param map        传参
     * @param observable 回调
     */
    public final void getWeatherInfo(Map<String, Object> map, Observer<WeatherEntity> observable) {
        ApiWrapper.getInstance()
                .create(IWeatherApi.class)
                .getWeatherInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable);
    }
}
