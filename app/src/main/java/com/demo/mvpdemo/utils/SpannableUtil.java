package com.demo.mvpdemo.utils;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

public class SpannableUtil {
    private SpannableString spanString;
    private int subscript = 0;

    public SpannableUtil(String showData) {
        spanString = new SpannableString(showData);
    }

    public SpannableString getSpanString(String[] strList, int[] sizeList, String[] colorList) {
        for (int i = 0; i < strList.length; i++) {
            String str = strList[i];
            int end_subscript = subscript + str.length();
            spanString.setSpan(new ForegroundColorSpan(Color.parseColor(colorList[i])), subscript, end_subscript, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanString.setSpan(new AbsoluteSizeSpan(sizeList[i]), subscript, end_subscript, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            subscript = end_subscript;
        }
        return spanString;
    }
}
