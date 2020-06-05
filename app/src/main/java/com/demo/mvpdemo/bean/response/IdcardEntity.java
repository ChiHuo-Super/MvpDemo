package com.demo.mvpdemo.bean.response;

import java.io.Serializable;

/**
 * @projectName：MvpDemo
 * @createTime：2020/6/5 17:21
 * @author：chihuo
 * @company：
 * @e-mail：
 * @description：身份证信息实体类
 */
public class IdcardEntity implements Serializable {
    private String province = "";//省
    private String city = "";//市
    private String town = "";//县
    private String lastflag = "";//最后一位效验码 0正确 1错误
    private String sex = "";//性别
    private String birth = "";//出生年月
    private String area = "";//区域信息,由于城市规划的原因，省市县变化较大。具体以此为准

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getLastflag() {
        return lastflag;
    }

    public void setLastflag(String lastflag) {
        this.lastflag = lastflag;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "IdcardEntity{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", town='" + town + '\'' +
                ", lastflag='" + lastflag + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
