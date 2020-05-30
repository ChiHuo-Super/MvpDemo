package com.demo.mvpdemo.presenter;

import android.content.Context;

import com.demo.mvpdemo.Constants;
import com.demo.mvpdemo.bean.response.WeatherEntity;
import com.demo.mvpdemo.controller.IMainController;
import com.demo.mvpdemo.model.BaseResultObserver;
import com.demo.mvpdemo.model.WeatherLoader;

import java.util.Map;
import java.util.TreeMap;

public class MainPresenter extends BasePresenter<IMainController.IView> implements IMainController.IPresenter {
    private final WeatherLoader weatherLoader;

    public MainPresenter(IMainController.IView mView, Context mContext) {
        super(mView, mContext);
        weatherLoader = new WeatherLoader();
    }

    @Override
    public void getWeatherData() {
        Constants.BASE_URL = "www.tianqiapi.com";
        getWeatherInfo();
    }


    private void getWeatherInfo() {
        Map<String, Object> map = new TreeMap<>();
        map.put("version", "V6");
        map.put("cityid", "101110101");
        weatherLoader.getWeatherInfo(map, new BaseResultObserver<WeatherEntity>(mContext) {
            @Override
            public void onSucceedListener(WeatherEntity weatherEntity) {
                mView.showWeatherData(weatherEntity.toString());
            }

            @Override
            public void onErrorListener(String e) {
                showToast(e);
            }

            @Override
            public void onOtherOperationrListener() {

            }
        });
    }
}
