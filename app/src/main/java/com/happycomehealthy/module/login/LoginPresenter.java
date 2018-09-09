package com.happycomehealthy.module.login;

import android.text.TextUtils;

import com.happycomehealthy.bean.Constants;
import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.utils.PreferenceUtils;
import com.happycomehealthy.utils.ToastUtils;

/**
 * creator: ZZF
 * careate date: 2018/5/18  09:24.
 */

public class LoginPresenter implements LoginContact.ILoginPresenter {

    private LoginContact.ILoginBiz ILoginBIz;
    private LoginContact.ILoginView ILoginView;

    public LoginPresenter(LoginContact.ILoginView loginActivity) {
        this.ILoginView = loginActivity;
        this.ILoginBIz = new LoginBiz();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    public void login(String account, String password) {

        if(TextUtils.isEmpty(account)){
            ToastUtils.showToast("账号没填写");
            return;
        }
        if(TextUtils.isEmpty(password)){
            ToastUtils.showToast("密码没填写");
            return;
        }

        String tempAccount = PreferenceUtils.getString(Constants.WORKER_ACCOUNT, "");
        String tempPwd = PreferenceUtils.getString(Constants.WORKER_PASSWORD, "");

        if(!account.equals(tempAccount)){
            ToastUtils.showToast("账号不正确");
            return;
        }


        if(!tempPwd.equals(password)){
            ToastUtils.showToast("密码不正确");
            return;
        }
        ILoginView.startMainActivity();
    }

}
