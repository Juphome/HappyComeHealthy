package com.happycomehealthy.module.search.searchguest;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.base.BaseBiz;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.base.BaseView;
import com.happycomehealthy.net.pojo.Guest;

/**
 * Created by shixinshan on 2018/5/5.
 */

public class SearchGuestContact {

    interface  ISearchGuestPresenter extends BasePresenter {


        /**
         * 根据用户ID，查询用户信息
         * @param person_id
         */
        void getUserInfo(String person_id);


    }
    interface ISearchGestView extends BaseView {
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
    interface  ISearchGuestBiz extends BaseBiz {



        /**
         * 根据用户ID，查询用户
         * @param operationListener
         */
        void getUser(String person_id, OperationListener operationListener);


    }
}
