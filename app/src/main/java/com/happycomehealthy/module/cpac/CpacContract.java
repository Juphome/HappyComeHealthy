package com.happycomehealthy.module.cpac;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.base.BaseBiz;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.base.BaseView;

/**
 * Created by shixinshan on 2018/5/29.
 */

public interface CpacContract {
    interface  ICpacPresenter extends BasePresenter {


        /**
         * 根据客人ID，查询后台数据库，获取客人某个月的签到日期
         * @param guest_id
         * @param todayDate
         */
        void getSignDatesInMonth(String guest_id,String todayDate);
    }
    interface ICpacView extends BaseView {



        void updateMonthInfo(int yyyy, int month);
    }
    interface  ICpacBiz extends BaseBiz {


        /**
         * 根据客人ID，查询后台数据库，获取客人某个月的签到日期
         * @param guest_id
         * @param todayDate
         * @param operationListener
         */
        void getSignDatesInMonth(String guest_id, String todayDate, OperationListener operationListener);
    }
}
