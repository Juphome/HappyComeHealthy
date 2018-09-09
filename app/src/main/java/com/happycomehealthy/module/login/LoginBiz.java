package com.happycomehealthy.module.login;

import com.happycomehealthy.listeners.OperationListener;

/**
 * creator: ZZF
 * careate date: 2018/5/18  09:25.
 */

public class LoginBiz implements LoginContact.ILoginBiz{
    @Override
    public void login(String account, String password, OperationListener operationListener) {
        operationListener.onDone(null);
    }

    @Override
    public void sign(String account, String password, OperationListener operationListener) {
        operationListener.onDone(null);
    }
}
