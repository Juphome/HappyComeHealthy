package com.happycomehealthy.module.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.happycomehealthy.R;
import com.happycomehealthy.module.base.BaseActivity;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.login.LoginPresenter;
import com.happycomehealthy.module.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterActivity extends BaseActivity implements RegisterContact.IRegisterView {


    private RegisterPresenter registerPresenter;

    @BindView(R.id.edt_account)
    EditText edt_account;
    @BindView(R.id.edt_password)
    EditText edt_password;
    @BindView(R.id.btn_register)
    Button btn_register;

    private Unbinder unbinder;

    @Override
    public int loadLayout() {
        return R.layout.activity_register;
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
        registerPresenter = new RegisterPresenter(this);
        return registerPresenter;
    }


    @OnClick(R.id.btn_register)
    public void register(){
        String account = edt_account.getText().toString();
        String password = edt_password.getText().toString();
        registerPresenter.register(account,password);
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
