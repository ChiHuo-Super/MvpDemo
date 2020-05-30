package com.demo.mvpdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 输入法工具
 * 
 * @author guoweijie
 * 
 */
public class InputMethodUtil {
	/**
	 * 关闭输入键盘
	 * 
	 * @param context
	 */
	public static void closeInputKeyboard(Context context) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		View focusView = ((Activity) context).getCurrentFocus();
		if (focusView != null) {
			imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
		}
	}

	/**
	 * 弹出输入法
	 * 
	 * @param context
	 * @param remindView
	 */
	public static void showInputKeyBord(Context context, View remindView) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (remindView != null) {
			remindView.requestFocus();
			imm.showSoftInput(remindView, 0);
		}
	}
}
