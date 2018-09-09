package com.happycomehealthy.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.happycomehealthy.R;
import com.happycomehealthy.module.base.BaseActivity;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.main.MainActivity;
import com.happycomehealthy.module.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends BaseActivity implements LoginContact.ILoginView {


    private LoginPresenter loginPresenter;

    @BindView(R.id.edt_account)
    EditText edt_account;
    @BindView(R.id.edt_password)
    EditText edt_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.tv_register)
    TextView tv_register;
    private Unbinder unbinder;

    @Override
    public int loadLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter setPresenter() {
        loginPresenter = new LoginPresenter(this);
        return loginPresenter;
    }

    @OnClick(R.id.btn_login)
    public void login(){
        String account = edt_account.getText().toString();
        String password = edt_password.getText().toString();
        loginPresenter.login(account,password);
    }
    @OnClick(R.id.tv_register)
    public void sign(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
