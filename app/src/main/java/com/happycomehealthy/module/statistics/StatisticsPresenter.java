package com.happycomehealthy.module.statistics;

import android.text.TextUtils;

import com.happycomehealthy.bean.CacheData;
import com.happycomehealthy.bean.DayGuest;
import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.statistics.StatisticsContact.IStatisticsPresenter;
import com.happycomehealthy.net.pojo.MonthStatisticBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shixinshan on 2018/6/19.
 */

public class StatisticsPresenter implements IStatisticsPresenter {
    private String TAG = StatisticsPresenter.class.getSimpleName();
    private  StatisticsBiz ibz;
    private  StatisticsContact.IStatisticsView iview;

    public StatisticsPresenter(StatisticsContact.IStatisticsView statisticsFragment) {

        this.iview = statisticsFragment;
        this.ibz = new StatisticsBiz();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    /**
     * 向后台请求某月的签到人数
     * @param whichMonth
     */
    public void getMonthStatisticData(String whichMonth) {
        if(TextUtils.isEmpty(whichMonth)){
            return;
        }
        try {
            String[] split = whichMonth.split("-");
            String yyyy = split[0];
            String month = split[1];
            if(Integer.parseInt(month) <= 9){
                month = "0"+month;
            }
            String tempDate = yyyy+"-"+month;
            ibz.getMonthStatisticsData(tempDate,new OperationListener<List<MonthStatisticBean>>(){
                @Override
                public void onDone(List<MonthStatisticBean> obj) {
                    if(obj != null){
                        CacheData.getInstance().setMonthStatisticBeanList(obj);
                        iview.showMonthStatistic();
                    }
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 向后台请求某一天的客户
     * @param whichday
     */
    public void getDayStatisticData(String whichday) {
        if(TextUtils.isEmpty(whichday)){
            return;
        }
        try {
            String[] split = whichday.split("-");
            String yyyy = split[0];
            String month = split[1];
            String day = split[2];
            if(Integer.parseInt(month) <= 9){
                month = "0"+month;
            }
            if(Integer.parseInt(day) <= 9){
                day = "0"+day;
            }
            String tempDate = yyyy+"-"+month+"-"+day;
            ibz.getDayStatisticsData(tempDate,new OperationListener<List<DayGuest>>(){
                @Override
                public void onDone(List<DayGuest> obj) {
                    if(obj != null){
                        showDayStatistic(obj);

                    }
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 显示某一天的客户签到与没有签到
     * @param obj
     */
    private void showDayStatistic(List<DayGuest> obj) {
        List<DayGuest> daySignedGuestList = new ArrayList<>();
        List<DayGuest> dayNotSignedGuestList = new ArrayList<>();
        for (int i=0;i<obj.size();i++){
            DayGuest dayGuest = obj.get(i);
            String signDate = dayGuest.getSignDate();
            if(!TextUtils.isEmpty(signDate)){
                daySignedGuestList.add(dayGuest);
            }else {
                dayNotSignedGuestList.add(dayGuest);
            }

        }
        CacheData.getInstance().setDaySignedGuestList(daySignedGuestList);
        CacheData.getInstance().setDayNotSignedGuestList(dayNotSignedGuestList);
        iview.showDayStatistic();
    }
}
