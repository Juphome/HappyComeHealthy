package com.happycomehealthy.module.sign;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.happycomehealthy.R;
import com.happycomehealthy.bean.ShowGuestEvent;
import com.happycomehealthy.module.base.BaseFragment;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.search.searchguest.SearchGuestFragment;
import com.happycomehealthy.net.pojo.Guest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;


/**
 * 用户签到
 * Created by shixinshan on 2018/5/3.
 */

public class SignFragment extends BaseFragment implements SignContact.ISignView{
    private SignPresenter signPresenter;






    public static SignFragment newInstance(String param1) {
        SignFragment fragment = new SignFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public SignFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        showSearchGuestFragment();

    }

    /**
     * 显示搜索客人的界面
     */
    private void showSearchGuestFragment() {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        SearchGuestFragment searchGuestFragment = SearchGuestFragment.newInstance();
        ft.add(R.id.fl_content,searchGuestFragment,SearchGuestFragment.class.getName());
        ft.addToBackStack(SearchGuestFragment.class.getName());
        ft.commitAllowingStateLoss();
    }


    @Override
    protected BasePresenter setPresenter() {
         this.signPresenter = new SignPresenter(this);
        return signPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sign;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void iniView() {

        showSeachView();
    }
    public void showSeachView(){
//        ll_search.setVisibility(View.VISIBLE);
//        ll_user_info.setVisibility(View.GONE);
    }
    public void showUserInfoView(){
//        ll_search.setVisibility(View.GONE);
//        ll_user_info.setVisibility(View.VISIBLE);
    }
    @Override
    public void showUserId(String user_id) {



    }

    @Override
    public void showUserInfo(Guest guest) {


    }


    @OnClick(R.id.btn_sign)
    public void sign(){

//        signPresenter.sign(user_id,user_name,sex,age,medical_history);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showSignPage(ShowGuestEvent event){
        showToast("显示顾客签到界面");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
