package com.happycomehealthy.bean;

import com.happycomehealthy.net.pojo.MonthStatisticBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存数据类
 * Created by shixinshan on 2018/8/14.
 */

public class CacheData {


    private List<MonthStatisticBean> monthStatisticBeanList;
    private List<DayGuest> daySignedGuestList = new ArrayList<>();
    private List<DayGuest> dayNotSignedGuestList = new ArrayList<>();
    private String guestName;


    private CacheData(){

    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestName() {
        return guestName;
    }

    private static class CacheDataHandler{
        public   static CacheData  instance = new CacheData();
    }
    public static CacheData getInstance(){
        return CacheDataHandler.instance;
    }

    public List<MonthStatisticBean> getMonthStatisticBeanList() {
        return monthStatisticBeanList;
    }

    public void setMonthStatisticBeanList(List<MonthStatisticBean> monthStatisticBeanList) {
        this.monthStatisticBeanList = monthStatisticBeanList;
    }

    public List<DayGuest> getDaySignedGuestList() {
        return daySignedGuestList;
    }

    public void setDaySignedGuestList(List<DayGuest> daySignedGuestList) {
        this.daySignedGuestList = daySignedGuestList;
    }

    public List<DayGuest> getDayNotSignedGuestList() {
        return dayNotSignedGuestList;
    }

    public void setDayNotSignedGuestList(List<DayGuest> dayNotSignedGuestList) {
        this.dayNotSignedGuestList = dayNotSignedGuestList;
    }
}
