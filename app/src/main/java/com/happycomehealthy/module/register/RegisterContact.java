package com.happycomehealthy.module.register;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.base.BaseBiz;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.base.BaseView;

/**
 * Created by shixinshan on 2018/5/3.
 */

public class RegisterContact {

    interface  IRegisterPresenter extends BasePresenter{

    }
    interface IRegisterView extends BaseView{

        /**
         * 启动主页面
         */
        void startMainActivity();
    }
    interface  IRegisterBiz extends BaseBiz{




        /**
         * 注册
         * @param account
         * @param password
         * @param operationListener
         */
        void register(String account, String password, OperationListener operationListener);
    }
}
