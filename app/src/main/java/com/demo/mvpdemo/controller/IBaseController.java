package com.demo.mvpdemo.controller;

import android.content.Intent;
import android.view.View;

public interface IBaseController {
    /**
     * 基础View应有的方法
     */
    interface IView {

        Intent getIntent();

        void showTips(String msg);

        void startActivity(Class className);

        void startActivity(Intent intent);

        void startActivityForResult(Class className, int requestCode);

        void startActivityForResult(Intent intent, int requestCode);

        void setResult(int resultCode, Intent data);

        void setResult(int resultCode);

        void finish();
    }

    /**
     * 基础Presenter 应有的方法
     */
    interface IPresenter {

        void onNewIntent();

        void onCreate();

        void onStart();

        void onResume();

        void onPause();

        void onStop();

        void onDestroy();

        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
