package com.demo.mvpdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.demo.mvpdemo.controller.IBaseController;
import com.demo.mvpdemo.utils.ClickUtils;
import com.demo.mvpdemo.utils.EventBusUtils;
import com.demo.mvpdemo.view.adapter.OnViewClickedListener;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 所有的Activity 都应继承自改类
 *
 * @param <T> Activity所对应的Presenter
 */
public abstract class BaseActivity<T extends IBaseController.IPresenter> extends AppCompatActivity implements IBaseController.IView {

    protected Context mContext;
    protected T mPresenter;

    private Unbinder mUnbinder;
    protected View rootLayout;

    private Disposable disposableCountDown;
    protected ClickUtils clickUtils;
    private OnViewClickedListener finishClickedListeners, timerClickedListeners;

    protected long backTime;
    protected boolean isShowView = true;//是否显示View

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (mPresenter != null) mPresenter.onNewIntent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCreate();
        if (mPresenter != null) mPresenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        isShowView = true;
        if (mPresenter != null) mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isShowView = true;
        if (mPresenter != null) mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isShowView = false;
        if (mPresenter != null) mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isShowView = false;
        if (mPresenter != null) mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
        if (EventBusUtils.isRegistered(this)) EventBusUtils.unregister(this);
        if (mUnbinder != null) mUnbinder.unbind();
        stopCountDown();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mPresenter != null) mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    public void initCreate() {
        isShowView = true;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initSystemData();
        setContentView(setContentLayout());
        mContext = this;
        mUnbinder = ButterKnife.bind(this);
        clickUtils = new ClickUtils();
        initView();
    }

    private void initView() {
        mPresenter = initPresenter();
        rootLayout = getRootLayout();
        initData();
    }

    /**
     * 获得根布局
     *
     * @return 根布局
     */
    @NonNull
    protected abstract View getRootLayout();

    /**
     * 获得 IPresenter
     *
     * @return IPresenter
     */
    protected abstract T initPresenter();

    /**
     * 返回当前页面布局文件
     *
     * @return @LayoutRes R.layout.x
     */
    @LayoutRes
    protected abstract int setContentLayout();

    /**
     * 初始化页面参数，在界面初始化之后
     */
    protected abstract void initData();


    /**
     * 初始化系统参数，在页面初始化之前
     */
    protected void initSystemData() {
    }

    /**
     * 显示提示
     *
     * @param msg
     */
    @Override
    public void showTips(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            //TODO 界面提示

            }
        });
    }

    @Override
    public void startActivity(Class className) {
        Intent intent = new Intent(mContext, className);
        startActivity(intent);
    }

    @Override
    public void startActivityForResult(Class className, int requestCode) {
        Intent intent = new Intent(mContext, className);
        startActivityForResult(intent, requestCode);
    }

    public void startCountDown(TextView textView, long time, String str) {
        startCountDown(textView, time, str, -1);
    }

    /**
     * 返回倒计时
     *
     * @param textView 承载控件
     * @param time     倒计时时长（秒）
     * @param str      显示文本
     * @param timeLis  触发事件指定倒计时时间点
     */
    public void startCountDown(TextView textView, long time, String str, int timeLis) {
        stopCountDown();
        disposableCountDown = Observable.intervalRange(0, time + 1, 0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> {
                    if (textView != null) {
                        textView.setText(String.format("%s(%ss)", str, (time - aLong)));
                        backTime = time - aLong;
                        if (timerClickedListeners != null && backTime == timeLis)
                            timerClickedListeners.onClick(textView);
                    }
                })
                .doOnComplete(() -> {
                    //倒计时完毕
                    if (finishClickedListeners != null) {
                        finishClickedListeners.onClick(textView);
                    }
                })
                .subscribe();
    }

    public void reStartCountDown(TextView textView, long time, String str) {
        reStartCountDown(textView, time, str, -1);
    }

    public void reStartCountDown(TextView textView, long time, String str, int timeLis) {
        if (disposableCountDown == null) {
            startCountDown(textView, time, str, timeLis);
            return;
        }
        if ((time - 3) < backTime) return;
        else startCountDown(textView, time, str, timeLis);
    }

    public void stopCountDown() {
        if (disposableCountDown != null && !disposableCountDown.isDisposed()) {
            disposableCountDown.dispose();
            disposableCountDown = null;
        }
    }

    /**
     * 绑定倒计时结束后触发的事件
     *
     * @param finishClickedListener
     */
    public void setFinishClickedListener(OnViewClickedListener finishClickedListener) {
        finishClickedListeners = finishClickedListener;
    }

    /**
     * 绑定指定时间触发的事件
     *
     * @param timerClickedListener
     */
    public void setTimerClickedListeners(OnViewClickedListener timerClickedListener) {
        timerClickedListeners = timerClickedListener;
    }

}
