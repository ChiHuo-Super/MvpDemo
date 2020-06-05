package com.demo.mvpdemo.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.demo.mvpdemo.controller.IBaseController;
import com.demo.mvpdemo.utils.EventBusUtils;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseController.IView> implements IBaseController.IPresenter, LifecycleObserver {

    protected Context mContext;
    protected WeakReference<V> mView;

    public BasePresenter(V mView, Context mContext) {
        this.mContext = mContext;
        attachView(mView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny(LifecycleOwner owner, Lifecycle.Event event) {
        //System.out.println("onAny:" + event.name());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        System.out.println("onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        System.out.println("onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        System.out.println("onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        System.out.println("onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() {
        System.out.println("onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        System.out.println("onDestroy");
        if (EventBusUtils.isRegistered(this)) EventBusUtils.unregister(this);
        detachView();
    }


    @Override
    public void onNewIntent() {
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
        return mView.get().getIntent();
    }

    /**
     * 关闭页面
     */
    protected void finish() {
        if (mView.get() != null) mView.get().finish();
    }

    /**
     * 关闭页面
     */
    protected void finishResult() {
        if (mView.get() != null) {
            mView.get().setResult(0);
            mView.get().finish();
        }
    }

    /**
     * 关闭页面
     */
    protected void finishResult(int resultCode) {
        if (mView.get() != null) {
            mView.get().setResult(resultCode);
            mView.get().finish();
        }
    }

    /**
     * 关闭页面
     */
    protected void finishResult(int resultCode, Intent data) {
        if (mView.get() != null) {
            mView.get().setResult(resultCode, data);
            mView.get().finish();
        }
    }

    /**
     * 跳转页面
     *
     * @param intent 意图
     */
    protected void startActivity(Intent intent) {
        if (mView.get() != null) mView.get().startActivity(intent);
    }

    /**
     * 跳转页面
     *
     * @param className 类名
     */
    protected void startActivity(Class className) {
        if (mView.get() != null) mView.get().startActivity(className);
    }

    protected void startActivity(Class className, String key, String str) {
        if (mView.get() != null) {
            Intent intent = new Intent(mContext, className);
            intent.putExtra(key, str);
        }
    }

    public void showToast(String str) {
        Toast.makeText(mContext, str, Toast.LENGTH_LONG).show();
    }

    /**
     * 绑定
     */
    private void attachView(V mView) {
        this.mView = new WeakReference<>(mView);
    }

    /**
     * 解绑定
     */
    private void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }
}
