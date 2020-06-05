package com.demo.mvpdemo.model;

import com.demo.mvpdemo.api.IIdcardApi;
import com.demo.mvpdemo.bean.response.BaseResult;
import com.demo.mvpdemo.bean.response.IdcardEntity;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @projectName：MvpDemo
 * @createTime：2020/6/5 17:25
 * @author：chihuo
 * @company：
 * @e-mail：
 * @description：
 */
public class IdcardLoader {

    /**
     * 查询身份证信息
     *
     * @param map        传参
     * @param observable 回调
     */
    public final void getIdcardInfo(Map<String, Object> map, Observer<BaseResult<IdcardEntity>> observable) {
        ApiWrapper.getInstance()
                .create(IIdcardApi.class)
                .getIdcardInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable);
    }
}
