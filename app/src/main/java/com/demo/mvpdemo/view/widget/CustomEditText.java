package com.demo.mvpdemo.view.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@SuppressLint("AppCompatCustomView")
public class CustomEditText extends EditText {
    private int maxLength = -1;
    private int maxSize = -1;
    private int minSize = -1;

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context) {
        Typeface fontFace = Typeface.createFromAsset(context.getAssets(), "fonts.ttf");
        setTypeface(fontFace);
    }

    @Override
    protected void onTextChanged(CharSequence charSequence, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(charSequence, start, lengthBefore, lengthAfter);
        String str = charSequence.toString().trim();
        setMaxLength(str);
        setNumberMaxSize(str);
        setNumberMinSize(str);
    }

    /**
     * 设置edittext最大的输入长度限制
     *
     * @param length
     */
    public void setMaxLength(int length) {
        this.maxLength = length;
    }

    /**
     * 设置edittext最大的输入最大值限制
     *
     * @param maxSize
     */
    public void setNumberMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 设置edittext最大的输入最小值限制
     *
     * @param minSize
     */
    public void setNumberMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxLength(String str) {
        if (maxLength == -1) return;
        if (str.length() > maxLength) {
            setText(str.substring(0, maxLength)); //截取前x位
            requestFocus();
            setSelection(getText().length()); //光标移动到最后
        }
    }

    public void setNumberMaxSize(String str) {
        if (maxSize == -1 || str.isEmpty()) return;
        if (Integer.parseInt(str) > maxSize) {
            setText(String.valueOf(maxSize));
        }
    }

    public void setNumberMinSize(String str) {
        if (minSize == -1 || str.isEmpty()) return;
        if (Integer.parseInt(str) < minSize) {
            setText(String.valueOf(minSize));
        }
    }
}
