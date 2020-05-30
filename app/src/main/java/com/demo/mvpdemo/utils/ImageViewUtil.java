package com.demo.mvpdemo.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.demo.mvpdemo.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImageViewUtil {

    /**
     * 初始化gif动画控件
     *
     * @param mContext      上下文对象
     * @param imageView     承载控件
     * @param resource_code 显示资源
     */
    public static void initImageView(Context mContext, ImageView imageView, int resource_code) {
        Glide.with(mContext)
                .asGif()
                .fitCenter()
                .load(resource_code)
                .into(imageView);
    }

    public static void initImageView(Context mContext, ImageView imageView, String resource_code) {
        Glide.with(mContext)
                .load(resource_code)
                .into(imageView);
    }

    /**
     * 设置头像
     *
     * @param context   上下文对象
     * @param cimgView  承载控件
     * @param PhotoData 照片数组(Base64)
     * @param PhotoUrl  照片URL
     */
    public static void setImagePhoto(Context context, CircleImageView cimgView, String PhotoData, String PhotoUrl) {
        if (cimgView == null) return;
        if (PhotoData != null && !PhotoData.isEmpty()) {
            cimgView.setImageBitmap(Base64Util.getStringToBitmap(PhotoData));
        } else if (PhotoUrl != null && !PhotoUrl.isEmpty()) {
            Glide.with(context)
                    .load(PhotoUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(cimgView);
        } else {
            cimgView.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }
}
