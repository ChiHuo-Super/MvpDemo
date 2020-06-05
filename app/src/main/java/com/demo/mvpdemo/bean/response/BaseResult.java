package com.demo.mvpdemo.bean.response;

import java.io.Serializable;

/**
 * @projectName：MvpDemo
 * @createTime：2020/6/5 15:19
 * @author：chihuo
 * @company：
 * @e-mail：
 * @description：
 */
public class BaseResult<T> implements Serializable {
    private String status = "";
    private String msg = "";
    private T result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
