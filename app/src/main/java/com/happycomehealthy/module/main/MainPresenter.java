package com.happycomehealthy.module.main;

import android.text.TextUtils;
import android.util.Log;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.net.pojo.Guest;
import com.happycomehealthy.utils.ToastUtils;

/**
 * Created by shixinshan on 2018/5/2.
 */

public class MainPresenter implements BasePresenter {

    private  MainBiz ibiz;
    private MainContract.IMainView iview ;

    public MainPresenter(MainContract.IMainView mainActivity) {
        this.iview = mainActivity;
        this.ibiz = new MainBiz();
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

    /**
     * 查询顾客的信息，如果查询到进行，显示
     * @param personal_id
     */
    public void searchGuestInfo(String personal_id) {
        if(TextUtils.isEmpty(personal_id)){
            ToastUtils.showToast("请输入客人的编号");
           return;
        }
        iview.showLoadingDialog("查询中。。。");
        ibiz.getGuest(personal_id, new OperationListener() {
            @Override
            public void onDone(Object obj) {
                Guest guest = (Guest) obj;
                Log.i("guest name = ", guest.getName());

                iview.showGuestInfo(guest);
                iview.hideLoadingDialog();
            }

            @Override
            public void onError(String errorMsg) {
                iview.showToast(errorMsg);
                iview.hideLoadingDialog();
            }
        });
    }
}
