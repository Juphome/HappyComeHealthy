package com.happycomehealthy.module.search;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.base.BaseBiz;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.base.BaseView;
import com.happycomehealthy.net.pojo.Guest;

/**
 * Created by shixinshan on 2018/5/29.
 */

public interface SearchContract {
    interface  ISearchPresenter extends BasePresenter {


        /**
         * 根据用户ID，查询用户信息
         * @param person_id
         */
        void getUserInfo(String person_id);

        /**
         * 签到
         * @param guest_id
         * @param guest_name
         * @param sex
         * @param age
         * @param medical_history
         */
        void sign(String guest_id, String guest_name, String sex, String age, String medical_history);
    }
    interface ISearchView extends BaseView {

    }
    interface  ISearchBiz extends BaseBiz {



        /**
         * 根据用户ID，查询用户
         * @param operationListener
         */
        void getUser(String person_id,OperationListener operationListener);

        /**
         * 签到
         * @param guest_id
         * @param guest_name
         * @param sex
         * @param age
         * @param medical_history
         * @param operationListener
         */
        void sign(String guest_id, String guest_name, String sex, String age, String medical_history, OperationListener operationListener);
    }
}
