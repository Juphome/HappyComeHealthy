package com.happycomehealthy.module.search.searchguest;

import android.util.Log;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.net.pojo.Guest;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by shixinshan on 2018/5/25.
 */

public class SearchGestPrestener implements SearchGuestContact.ISearchGuestPresenter{

    private  SearchGuestContact.ISearchGestView iview;
    private  SearchGuestBiz iBiz;

    public SearchGestPrestener(SearchGuestContact.ISearchGestView searchGestView) {
        this.iview = searchGestView;
        this.iBiz = new SearchGuestBiz();
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
}
