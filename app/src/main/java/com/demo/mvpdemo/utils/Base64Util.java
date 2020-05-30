package com.demo.mvpdemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


public class Base64Util {

    /**
     * 对给定的字符串进行base64解码操作
     */
    public static String decodeData(String inputData) {
        try {
            if (null == inputData) return null;
            return new String(Base64.decode(inputData, Base64.DEFAULT));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对给定的字符串进行base64加密操作
     */
    public static String encodeData(String inputData) {
        try {
            if (null == inputData) return null;
            return Base64.encodeToString(inputData.getBytes(), Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将base64转换成bitmap图片
     *
     * @param string base64字符串
     * @return bitmap
     */

    public static Bitmap getStringToBitmap(String string) {
        // 将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
