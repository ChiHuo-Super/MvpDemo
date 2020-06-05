package com.demo.mvpdemo.controller;

import androidx.lifecycle.MutableLiveData;

public interface IMainController {
    interface IView extends IBaseController.IView {
        void showIdcardData(String msg);
    }

    interface IPresenter extends IBaseController.IPresenter {

        MutableLiveData<String> getShowIdCradInfo();

        void queryIdcardData(String idcard);
    }
}
