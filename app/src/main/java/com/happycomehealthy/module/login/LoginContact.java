package com.happycomehealthy.module.login;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.base.BaseBiz;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.base.BaseView;

/**
 * Created by shixinshan on 2018/5/3.
 */

public class LoginContact {

    interface  ILoginPresenter extends BasePresenter{

    }
    interface ILoginView extends BaseView{

        /**
         * 启动主页面
         */
        void startMainActivity();
    }
    interface  ILoginBiz extends BaseBiz{


        /**
         * 登录
         * @param account
         * @param password
         * @param operationListener
         */
        void login(String account, String password, OperationListener operationListener);

        /**
         * 注册
         * @param account
         * @param password
         * @param operationListener
         */
        void sign(String account, String password, OperationListener operationListener);
    }
}
