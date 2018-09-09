package com.happycomehealthy.bean;

/**
 * 某一天从后台查询到客户的信息
 * Created by shixinshan on 2018/8/23.
 */

public class DayGuest {
    private String name;//客户名字
    private String picStr;//保存图片的路径
    private String personId;//个人编码
    private String signDate;//签到时间

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicStr() {
        return picStr;
    }

    public void setPicStr(String picStr) {
        this.picStr = picStr;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
