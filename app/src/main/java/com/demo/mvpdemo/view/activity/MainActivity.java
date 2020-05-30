package com.demo.mvpdemo.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.demo.mvpdemo.R;
import com.demo.mvpdemo.controller.IMainController;
import com.demo.mvpdemo.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<IMainController.IPresenter> implements IMainController.IView {

    @BindView(R.id.rootLayout)
    ConstraintLayout rootLayout;
    @BindView(R.id.txt_test)
    TextView txt_test;
    @BindView(R.id.btn_test)
    Button btn_test;

    @NonNull
    @Override
    protected View getRootLayout() {
        return rootLayout;
    }

    @Override
    protected IMainController.IPresenter initPresenter() {
        return new MainPresenter(this, mContext);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showWeatherData(String msg) {
        txt_test.setText(msg);
    }

    @OnClick(R.id.btn_test)
    public void onBtnTestClicked() {
        mPresenter.getWeatherData();
    }
}
