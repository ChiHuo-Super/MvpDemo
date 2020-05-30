package com.demo.mvpdemo.utils;

/**
 * 防重复点击
 */
public class ClickUtils {
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0L;

    public ClickUtils() {
    }

    public ClickUtils(int delayTime) {
        this.MIN_CLICK_DELAY_TIME = delayTime;
    }

    /**
     * 防爆点
     *
     * @return true:可触发，false:不可触发
     */
    public boolean isFastClick() {
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            lastClickTime = curClickTime;
            return true;
        } else {
            return false;
        }
    }
}
