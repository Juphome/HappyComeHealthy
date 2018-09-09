package com.happycomehealthy.module.search;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.happycomehealthy.R;
import com.happycomehealthy.bean.CacheData;
import com.happycomehealthy.bean.Constants;
import com.happycomehealthy.bean.ShowGuestEvent;
import com.happycomehealthy.module.base.BaseActivity;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.comment.CommentActivity;
import com.happycomehealthy.module.cpac.CpacActivity;
import com.happycomehealthy.module.test.mydatepicker.DPCManager;
import com.happycomehealthy.net.pojo.Guest;
import com.happycomehealthy.utils.ToastUtils;
import com.happycomehealthy.widget.TextItemVew;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchActivity extends BaseActivity implements SearchContract.ISearchView{
    private SearchView searchView;
    private SearchPresenter searchPresenter;
    private Unbinder unbinder;
    private DPCManager dpcManager;

    private String TAG = SearchActivity.class.getSimpleName();

    @BindView(R.id.tiv_user_name)
    TextItemVew tiv_user_name;

    @BindView(R.id.tiv_user_id)
    TextItemVew tiv_user_id;
    @BindView(R.id.tiv_age)
    TextItemVew tiv_age;
    @BindView(R.id.tiv_sex)
    TextItemVew tiv_sex;
    @BindView(R.id.tiv_phoneNumber)
    TextItemVew tiv_phoneNumber;

     @BindView(R.id.tiv_address)
    TextItemVew tiv_address;


    @BindView(R.id.tiv_introducer)
    TextItemVew tiv_introducer;

    @BindView(R.id.tiv_medical_history)
    TextItemVew tiv_medical_history;

    @BindView(R.id.tiv_personal_sign_detail)
    TextItemVew tiv_personal_sign_detail;

    @BindView(R.id.tiv_feedback)
    TextItemVew tiv_feedback;


    @BindView(R.id.btn_sign)
    Button btn_sign;



    @Override
    public int loadLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this);
        initToolBar();
    }

    @Override
    public void initData() {
        Guest guest = getIntent().getParcelableExtra(Constants.GUEST);
        showUserInfo(guest);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//这句代码使启用Activity回退功能，并显示Toolbar上的左侧回退图标
    }

    @Override
    public BasePresenter setPresenter() {
        searchPresenter = new SearchPresenter(this);
        return searchPresenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return super.onCreateOptionsMenu(menu);
    }

    public void showUserInfo(Guest guest) {
        if(guest == null){
            ToastUtils.showToast("客人数据空");
            return;
        }
        tiv_user_id.getTv_right_text().setText(guest.getPersonId());
        tiv_user_name.getTv_right_text().setText(guest.getName());
        tiv_phoneNumber.getTv_right_text().setText(guest.getPhoneNumber());
        tiv_age.getTv_right_text().setText(guest.getAge());
        tiv_address.getTv_right_text().setText(guest.getAddress());
        tiv_introducer.getTv_right_text().setText(guest.getIntroducer());
        tiv_medical_history.getTv_right_text().setText(guest.getMedical_history());
        tiv_sex.getTv_right_text().setText(guest.getSex());

        if(guest.isSigned()){
            btn_sign.setEnabled(false);
            btn_sign.setText(R.string.has_signed);
//            Drawable drawable = ContextCompat.getDrawable(this,,R.drawable.selector_green_bg);
//            drawable.setBounds(20, 0, 0, 0);
//            btn_sign.setBackgroundResource(R.drawable.selector_green_bg);

//            btn_sign.setBackgroundColor(Color.parseColor("#00CC33"));
        }else {
            btn_sign.setEnabled(true);
            btn_sign.setText(R.string.has_not_signed);
//            btn_sign.setBackgroundResource(R.drawable.selector_orange_bg);
//            btn_sign.setBackgroundColor(Color.parseColor("#FC9D04"));
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
    @OnClick(R.id.btn_sign)
    public void sign(){

        String guest_id = tiv_user_id.getTv_right_text().getText().toString();
        String guest_name = tiv_user_name.getTv_right_text().getText().toString();
        String sex = tiv_sex.getTv_right_text().getText().toString();
        String age = tiv_age.getTv_right_text().getText().toString();
        String medical_history = tiv_medical_history.getTv_right_text().getText().toString();

        searchPresenter.sign(guest_id,guest_name,sex,age,medical_history);
    }

    @OnClick(R.id.tiv_personal_sign_detail)
    public void personalSignDetail(){

        Log.i(TAG,"tiv_personal_sign_detail");

        Intent intent = new Intent(this, CpacActivity.class);
        String guest_id = tiv_user_id.getTv_right_text().getText().toString();
        intent.putExtra(Constants.GUEST_ID,guest_id);
        startActivity(intent);
    }

    @OnClick(R.id.tiv_feedback)
    public void showFeedback(){
        Intent intent = new Intent(this, CommentActivity.class);
        String guest_name = tiv_user_name.getTv_right_text().getText().toString();
        CacheData.getInstance().setGuestName(guest_name);
        startActivity(intent);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showSignPage(ShowGuestEvent event){
        showToast("显示顾客签到界面");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null)
            unbinder.unbind();
    }

}
