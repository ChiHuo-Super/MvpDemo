package com.demo.mvpdemo.utils;

import com.demo.mvpdemo.BuildConfig;

public class AppUtil {

    /**
     * 获取App版本号
     *
     * @return App版本号
     */
    public static String getAppVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    /**
     * 获取App版本号
     *
     * @return App版本号
     */
    public static int getAppVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

}
