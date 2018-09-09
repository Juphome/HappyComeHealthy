package com.happycomehealthy.module.statistics;

import com.happycomehealthy.bean.DayGuest;
import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.base.BaseBiz;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.base.BaseView;
import com.happycomehealthy.net.pojo.Guest;
import com.happycomehealthy.net.pojo.MonthStatisticBean;

import java.util.List;

/**
 * Created by shixinshan on 2018/5/5.
 */

public class StatisticsContact {

    interface  IStatisticsPresenter extends BasePresenter {

    }
    interface IStatisticsView extends BaseView {

        /**
         * 显示月统计界面
         */
        void showMonthStatistic();

        /**
         * 显示某天签到的客户
         */
        void showDayStatistic();
    }
    interface  IStatisticsBiz extends BaseBiz {


        /**
         * 获取某月的统计数据
         * @param month
         * @param operationListener
         */
        void getMonthStatisticsData(String month,OperationListener<List<MonthStatisticBean>> operationListener);

        /**
         * 获取某一天的客户
         * @param tempDate
         * @param operationListener
         */
        void getDayStatisticsData(String tempDate, OperationListener<List<DayGuest>> operationListener);
    }
}
