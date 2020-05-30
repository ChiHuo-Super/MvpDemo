package com.demo.mvpdemo.model;

import android.content.Context;

import com.demo.mvpdemo.view.dialog.LoadingDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseResultObserver<T> implements Observer<T> {


    private Disposable disposable;
    private LoadingDialog loadingDialog;
    private Context mContext;

    public BaseResultObserver(Context mContext) {
        this.mContext = mContext;
        loadingDialog = LoadingDialog.getInstance(mContext);
    }

    public BaseResultObserver(Context mContext, boolean isLogin) {
        this.mContext = mContext;
        if (isLogin) {
            loadingDialog = LoadingDialog.getInstance(mContext);
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
        onSubscribeListener();
    }

    @Override
    public void onNext(T t) {
        if (mContext != null) {
            onSucceedListener(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mContext != null) onError(e, true);
    }

    public void onError(Throwable e, boolean isListener) {
        if (isListener) onErrorListener("报错：" + e.getMessage());
        if (loadingDialog != null) loadingDialog.dismissLoadingDialog();
        if (disposable != null) disposable.dispose();
    }

    /**
     * 回调成功事件
     */
    public abstract void onSucceedListener(T t);

    /**
     * 错误事件
     *
     * @param e
     */
    public abstract void onErrorListener(String e);

    /**
     * 实现其他附加操作
     */
    public abstract void onOtherOperationrListener();

    /**
     * 请求开始时
     */
    public void onSubscribeListener() {
        showLoadingDialog("Loading...");
    }

    public void showLoadingDialog(String mag) {
        if (loadingDialog != null) loadingDialog.showLoadingDialog(mag);
    }

    /**
     * 请求完成
     */
    public void onCompleteListener() {
    }

    @Override
    public void onComplete() {
        if (loadingDialog != null) loadingDialog.dismissLoadingDialog();
        if (disposable != null) disposable.dispose();
        onCompleteListener();
    }
}
