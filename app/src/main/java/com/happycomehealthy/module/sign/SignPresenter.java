package com.happycomehealthy.module.sign;

import android.util.Log;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.sign.SignContact.ISignPresenter;
import com.happycomehealthy.net.pojo.Guest;
import com.happycomehealthy.utils.ToastUtils;

/**
 * Created by shixinshan on 2018/5/5.
 */

public class SignPresenter implements ISignPresenter{

    private  SignBiz iBiz;
    private  SignContact.ISignView iview;

    public SignPresenter(SignContact.ISignView signFragment) {
        this.iview = signFragment;
        this.iBiz = new SignBiz();
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

    @Override
    public void getUserInfo(String person_id) {
        iview.showLoadingDialog("查询中。。。");
        iBiz.getUser(person_id, new OperationListener() {
            @Override
            public void onDone(Object obj) {
                Guest guest = (Guest) obj;
                Log.i("guest name = ", guest.getName());

                iview.showUserInfo(guest);
                iview.hideLoadingDialog();
            }

            @Override
            public void onError(String errorMsg) {

                iview.showToast(errorMsg);
                iview.hideLoadingDialog();

//                Guest user = new Guest();
//                user.setName("丽萍");
//                user.setAddress("惠州");
//                user.setAge("28");
//                user.setIntroducer("朋友");
//                user.setPersonId("1");
//                user.setMedical_history("无");
//                user.setSex("女");
//                user.setPhoneNumber("159******");
//                user.setSigned(false);
//                Log.i("user name = ",user.getName());
//
//
//                iview.showUserInfo(user);
            }
        });
    }

    @Override
    public void sign(String user_id, String user_name, String sex, String age, String medical_history) {
        iBiz.sign(user_id,user_name,sex,age,medical_history,new OperationListener(){
            @Override
            public void onDone(Object obj) {

            }

            @Override
            public void onError(String errorMsg) {
                ToastUtils.showToast(errorMsg);
            }
        });
    }
}
