package com.happycomehealthy.module.sign;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.base.BaseBiz;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.base.BaseView;
import com.happycomehealthy.net.pojo.Guest;

/**
 * Created by shixinshan on 2018/5/5.
 */

public class SignContact {

    interface  ISignPresenter extends BasePresenter {


        /**
         * 根据用户ID，查询用户信息
         * @param person_id
         */
        void getUserInfo(String person_id);

        /**
         * 签到
         * @param user_id
         * @param user_name
         * @param sex
         * @param age
         * @param medical_history
         */
        void sign(String user_id, String user_name, String sex, String age, String medical_history);
    }
    interface ISignView extends BaseView {
        /**
         * 显示用户ID
         * @param user_id
         */
        void showUserId(String user_id);

        /**
         * 显示搜索到到用户信息
         * @param guest
         */
        void showUserInfo(Guest guest);
    }
    interface  ISignBiz extends BaseBiz {



        /**
         * 根据用户ID，查询用户
         * @param operationListener
         */
        void getUser(String person_id,OperationListener operationListener);

        /**
         * 签到
         * @param user_id
         * @param user_name
         * @param sex
         * @param age
         * @param medical_history
         * @param operationListener
         */
        void sign(String user_id, String user_name, String sex, String age, String medical_history, OperationListener operationListener);
    }
}
