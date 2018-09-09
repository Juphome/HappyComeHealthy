package com.happycomehealthy.module.search;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.utils.ToastUtils;

/**
 * Created by shixinshan on 2018/5/29.
 */

public class SearchPresenter implements SearchContract.ISearchPresenter{


    private SearchBiz iBiz;
    private SearchContract.ISearchView iview;

    public SearchPresenter(SearchContract.ISearchView searchActivity) {
        this.iview = searchActivity;
        this.iBiz = new SearchBiz();
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

    }

    @Override
    public void sign(String guest_id, String guest_name, String sex, String age, String medical_history) {
        iBiz.sign(guest_id,guest_name,sex,age,medical_history,new OperationListener(){
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
