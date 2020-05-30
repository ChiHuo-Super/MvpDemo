package com.demo.mvpdemo.utils;

import android.content.Context;
import android.content.Intent;

public class ToActivityUtil {

    /**
     * 根据 class 直接跳转 Activity
     *
     * @param mContext  上下文对象
     * @param className class文件 X.class
     */
    public static void startActivity(Context mContext, Class className) {
        Intent intent = new Intent(mContext, className);
        mContext.startActivity(intent);
    }

    public void startActivity(Context mContext, Class className, String key, String str) {
        Intent intent = new Intent(mContext, className);
        intent.putExtra(key, str);
        mContext.startActivity(intent);
    }

}
