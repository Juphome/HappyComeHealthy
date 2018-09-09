package com.happycomehealthy.module.main;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.base.BaseBiz;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.base.BaseView;
import com.happycomehealthy.net.pojo.Guest;

/**
 * Created by shixinshan on 2018/6/1.
 */

public class MainContract {

    interface  IMainPresenter extends BasePresenter {


        /**
         * 根据用户ID，查询用户信息
         * @param person_id
         */
        void getUserInfo(String person_id);


    }
    interface IMainView extends BaseView {


        /**
         * 显示客人信息
         * @param guest
         */
        void showGuestInfo(Guest guest);
    }
    interface  IMainBiz extends BaseBiz {



        /**
         * 根据客人ID，查询客人
         * @param operationListener
         */
        void getGuest(String person_id,OperationListener operationListener);


    }
}
