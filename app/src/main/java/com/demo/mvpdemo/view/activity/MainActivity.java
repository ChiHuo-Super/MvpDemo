package com.demo.mvpdemo.view.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;

import com.demo.mvpdemo.R;
import com.demo.mvpdemo.controller.IMainController;
import com.demo.mvpdemo.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<IMainController.IPresenter> implements IMainController.IView {

    @BindView(R.id.rootLayout)
    ConstraintLayout rootLayout;
    @BindView(R.id.edt_idcard)
    EditText edtIdcard;
    @BindView(R.id.txt_test)
    TextView txtTest;


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
        mPresenter.getShowIdCradInfo().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtTest.setText(s);
            }
        });
    }

    @OnClick(R.id.btn_query)
    public void onBtnQueryClicked() {
        String idCardStr = edtIdcard.getText().toString().trim();
        if (!idCardStr.isEmpty()) {
            mPresenter.queryIdcardData(idCardStr);
        }
    }

    @Override
    public void showIdcardData(String msg) {

    }
}
