package com.demo.mvpdemo.controller;

public interface IMainController {
    interface IView extends IBaseController.IView {
        void showWeatherData(String msg);
    }

    interface IPresenter extends IBaseController.IPresenter {
        void getWeatherData();
    }
}
