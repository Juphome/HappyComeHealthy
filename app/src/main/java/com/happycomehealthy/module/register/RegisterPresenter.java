package com.happycomehealthy.module.register;

import android.text.TextUtils;

import com.happycomehealthy.bean.Constants;
import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.login.LoginBiz;
import com.happycomehealthy.module.login.LoginContact;
import com.happycomehealthy.utils.PreferenceUtils;
import com.happycomehealthy.utils.ToastUtils;

/**
 * creator: ZZF
 * careate date: 2018/5/18  09:24.
 */

public class RegisterPresenter implements RegisterContact.IRegisterPresenter {

    private RegisterContact.IRegisterBiz ILoginBIz;
    private RegisterContact.IRegisterView iRegisterView;

    public RegisterPresenter(RegisterContact.IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
        this.ILoginBIz = new RegisterBiz();
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



    public void register(String account, String password) {

        if(TextUtils.isEmpty(account)){
            ToastUtils.showToast("账号没填写");
            return;
        }
        if(TextUtils.isEmpty(password)){
            ToastUtils.showToast("密码没填写");
            return;
        }

        //TODO: 暂时保存账号和密码
        PreferenceUtils.putString(Constants.WORKER_ACCOUNT,account);
        PreferenceUtils.putString(Constants.WORKER_PASSWORD,password);

        iRegisterView.startMainActivity();
    }
}
