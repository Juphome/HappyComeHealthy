package com.happycomehealthy.module.mine;


import android.content.Intent;
import android.os.Bundle;

import com.happycomehealthy.R;
import com.happycomehealthy.bean.Constants;
import com.happycomehealthy.module.base.BaseFragment;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.flash.FlashActivity;
import com.happycomehealthy.module.login.LoginActivity;
import com.happycomehealthy.utils.PreferenceUtils;
import com.happycomehealthy.widget.TextItemVew;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 用户签到
 * Created by shixinshan on 2018/5/3.
 */

public class MineFragment extends BaseFragment implements MineContact.IMineView{


    @BindView(R.id.tiv_user__name)
    TextItemVew tiv_user__name;

    @BindView(R.id.tiv_exit)
    TextItemVew tiv_exit;
    private MinePresenter minePresenter;

    public static MineFragment newInstance(String param1) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public MineFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    protected BasePresenter setPresenter() {
        minePresenter = new MinePresenter();
        return minePresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {

        String account = PreferenceUtils.getString(Constants.WORKER_ACCOUNT, "");
        tiv_user__name.getTv_right_text().setText(account);
    }

    @OnClick(R.id.tiv_exit)
    public void exit(){
        Intent intent=new Intent(getActivity(),LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
    @Override
    protected void iniView() {

    }

    @Override
    public void showToast(String msg) {

    }
}
