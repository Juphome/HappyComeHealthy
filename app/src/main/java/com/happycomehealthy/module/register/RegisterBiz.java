package com.happycomehealthy.module.register;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.login.LoginContact;

/**
 * creator: ZZF
 * careate date: 2018/5/18  09:25.
 */

public class RegisterBiz implements RegisterContact.IRegisterBiz{

    @Override
    public void register(String account, String password, OperationListener operationListener) {
        operationListener.onDone(null);
    }
}
