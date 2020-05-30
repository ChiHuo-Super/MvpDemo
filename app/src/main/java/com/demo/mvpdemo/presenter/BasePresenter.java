package com.demo.mvpdemo.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.demo.mvpdemo.controller.IBaseController;
import com.demo.mvpdemo.utils.EventBusUtils;

public abstract class BasePresenter<V extends IBaseController.IView> implements IBaseController.IPresenter {

    protected Context mContext;
    protected V mView;

    public BasePresenter(V mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void onNewIntent() {
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onStop() {
    }


    @Override
    public void onDestroy() {
        if (EventBusUtils.isRegistered(this)) EventBusUtils.unregister(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    /**
     * 获取意图
     *
     * @return
     */
    protected Intent getIntent() {
        return mView.getIntent();
    }

    /**
     * 关闭页面
     */
    protected void finish() {
        if (mView != null) mView.finish();
    }

    /**
     * 关闭页面
     */
    protected void finishResult() {
        if (mView != null) {
            mView.setResult(0);
            mView.finish();
        }
    }

    /**
     * 关闭页面
     */
    protected void finishResult(int resultCode) {
        if (mView != null) {
            mView.setResult(resultCode);
            mView.finish();
        }
    }

    /**
     * 关闭页面
     */
    protected void finishResult(int resultCode, Intent data) {
        if (mView != null) {
            mView.setResult(resultCode, data);
            mView.finish();
        }
    }

    /**
     * 跳转页面
     *
     * @param intent 意图
     */
    protected void startActivity(Intent intent) {
        if (mView != null) mView.startActivity(intent);
    }

    /**
     * 跳转页面
     *
     * @param className 类名
     */
    protected void startActivity(Class className) {
        if (mView != null) mView.startActivity(className);
    }

    protected void startActivity(Class className, String key, String str) {
        if (mView != null) {
            Intent intent = new Intent(mContext, className);
            intent.putExtra(key, str);
        }
    }

    public void showToast( String str) {
        Toast.makeText(mContext, str, Toast.LENGTH_LONG).show();
    }
}
