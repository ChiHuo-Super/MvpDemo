package com.demo.mvpdemo.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;

/**
 * @projectName：MvpDemo
 * @createTime：2020/6/5 17:21
 * @author：chihuo
 * @company：
 * @e-mail：
 * @description：身份证信息表
 */
@Entity
public class IdcardTable   {

    @Id(autoincrement = true)
    public Long id;

    /**
     * 省
     */
    private String province = "";

    /**
     * 市
     */
    private String city = "";

    /**
     * 县
     */
    private String town = "";

    /**
     * 最后一位效验码 0正确 1错误
     */
    private String lastflag = "";

    /**
     * 性别
     */
    private String sex = "";

    /**
     * 出生年月
     */
    private String birth = "";

    /**
     * 区域信息,由于城市规划的原因，省市县变化较大。具体以此为准
     */
    private String area = "";

    public Date creadDate;

    public Date endDate;


    @Generated(hash = 2076166108)
    public IdcardTable(Long id, String province, String city, String town,
            String lastflag, String sex, String birth, String area, Date creadDate,
            Date endDate) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.town = town;
        this.lastflag = lastflag;
        this.sex = sex;
        this.birth = birth;
        this.area = area;
        this.creadDate = creadDate;
        this.endDate = endDate;
    }

    @Generated(hash = 1908572221)
    public IdcardTable() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getLastflag() {
        return this.lastflag;
    }

    public void setLastflag(String lastflag) {
        this.lastflag = lastflag;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return this.birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getCreadDate() {
        return this.creadDate;
    }

    public void setCreadDate(Date creadDate) {
        this.creadDate = creadDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


}
