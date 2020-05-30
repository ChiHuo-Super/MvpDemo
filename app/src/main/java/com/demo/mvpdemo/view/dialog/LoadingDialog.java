package com.demo.mvpdemo.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.demo.mvpdemo.R;
import com.demo.mvpdemo.view.widget.CustomTextView;

/**
 * 默认的加载进度条
 */
public class LoadingDialog {

    private Context mContext;
    private static LoadingDialog loadingDialog = null;
    private Dialog dialog;
    private CustomTextView tvLoading;

    private LoadingDialog(Context mContext) {
        this.mContext = mContext;
    }

    public static LoadingDialog getInstance(@NonNull Context mContext) {
        if (loadingDialog == null) {
            return new LoadingDialog(mContext);
        } else {
            return loadingDialog;
        }
    }

    /**
     * 展示加载框
     */
    public void showLoadingDialog() {
        showLoadingDialog("loading...");
    }


    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /**
     * 展示加载框
     */
    public void showLoadingDialog(String msg) {
        try {
            if (isMainThread()) {
                showDialogs(msg);
            } else {
                Activity activity = null;
                if (mContext != null && mContext instanceof Activity) {
                    activity = (Activity) mContext;
                }
                if (activity != null) {
                    activity.runOnUiThread(() -> showDialogs(msg));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDialogs(String msg) {
        if (mContext == null) return;
        Activity activity = null;
        if (mContext instanceof Activity) activity = (Activity) mContext;
        if (dialog != null && dialog.isShowing()) return;
        dialog = new Dialog(mContext, R.style.Dialog);
        View view = View.inflate(mContext, R.layout.dialog_loading, null);
        ImageView imageView = view.findViewById(R.id.iv_loading);
        Glide.with(mContext)
                .asGif()
                .fitCenter()
                .skipMemoryCache(true) // 不使用内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(R.drawable.gif_loading)
                .into(imageView);
        tvLoading = view.findViewById(R.id.tv_loading);
        dialog.setContentView(view);
        tvLoading.setText(msg);
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        if (activity != null && !activity.isFinishing()) dialog.show();
    }

    public void setLoadMsg(String msg) {
        if (tvLoading != null) tvLoading.setText(msg);
    }

    /**
     * 关闭加载框
     */
    public void dismissLoadingDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.cancel();
                dialog.dismiss();
            }
            loadingDialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
