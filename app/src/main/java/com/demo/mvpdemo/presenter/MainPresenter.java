package com.demo.mvpdemo.presenter;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.alibaba.fastjson.JSON;
import com.demo.mvpdemo.Constants;
import com.demo.mvpdemo.bean.response.BaseResult;
import com.demo.mvpdemo.bean.response.IdcardEntity;
import com.demo.mvpdemo.controller.IMainController;
import com.demo.mvpdemo.model.BaseResultObserver;
import com.demo.mvpdemo.model.IdcardLoader;

import java.util.Map;
import java.util.TreeMap;

public class MainPresenter extends BasePresenter<IMainController.IView> implements IMainController.IPresenter {
    private final IdcardLoader idcardLoader;
    private MutableLiveData<String> showIdCradInfo;

    public MainPresenter(IMainController.IView mView, Context mContext) {
        super(mView, mContext);
        idcardLoader = new IdcardLoader();
    }

    @Override
    public MutableLiveData<String> getShowIdCradInfo() {
        if (showIdCradInfo == null) {
            showIdCradInfo = new MutableLiveData<>();
            showIdCradInfo.setValue("");
        }
        return showIdCradInfo;
    }

    @Override
    public void queryIdcardData(String idcard) {
        showIdCradInfo.setValue("");
        getIdcardInfo(idcard);
    }

    /**
     * 查询身份证信息
     *
     * @param idcard 身份证或身份证前6位
     */
    private void getIdcardInfo(String idcard) {
        Map<String, Object> map = new TreeMap<>();
        map.put("appkey", Constants.APP_KEY);
        map.put("idcard", idcard);
        idcardLoader.getIdcardInfo(map, new BaseResultObserver<BaseResult<IdcardEntity>>(mContext) {
            @Override
            public void onSucceedListener(BaseResult<IdcardEntity> idcardEntityBaseResult) {
                if (!idcardEntityBaseResult.getStatus().equals("0")) {
                    onErrorListener(idcardEntityBaseResult.getMsg());
                    return;
                }
                IdcardEntity entity = idcardEntityBaseResult.getResult();
                if (entity != null) {
                    showIdCradInfo.setValue(entity.toString());
                }
            }


            @Override
            public void onErrorListener(String e) {
                showIdCradInfo.setValue("获取身份证信息失败：" + e);
            }

            @Override
            public void onOtherOperationrListener() {

            }
        });
    }


}
