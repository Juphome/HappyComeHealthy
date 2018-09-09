package com.happycomehealthy.module.edit;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.happycomehealthy.R;
import com.happycomehealthy.module.base.BaseFragment;
import com.happycomehealthy.module.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑用户
 * Created by shixinshan on 2018/5/3.
 */

public class EditFragment extends  BaseFragment implements EditContact.IEditView{

    @BindView(R.id.tv_user_id)
    TextView tv_user_id;
    @BindView(R.id.btn_user_id)
    Button btn_user_id;
    @BindView(R.id.edt_user_name)
    EditText edt_user_name;
    @BindView(R.id.edt_user_age)
    EditText edt_user_age;
    @BindView(R.id.edt_address)
    EditText edt_address;
    @BindView(R.id.rb_male)
    RadioButton rb_male;
    @BindView(R.id.rb_female)
    RadioButton rb_female;
    @BindView(R.id.edt_phoneNumber)
    EditText edt_phoneNumber;

    @BindView(R.id.edt_introducer)
    EditText edt_introducer;
    @BindView(R.id.edt_medical_history)
    EditText edt_medical_history;
    @BindView(R.id.btn_upload_uer_data)
    Button btn_upload_uer_data;

    private boolean isMale = true;
    private String TAG = EditFragment.class.getSimpleName();
    private EditPresenter editPresenter;

    public static EditFragment newInstance(String param1) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public EditFragment() {


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected EditPresenter setPresenter() {
         editPresenter = new EditPresenter(this);
        return editPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit;
    }

    @Override
    protected void initData() {
        RadioGroup radioGroup = (RadioGroup)mView.findViewById(R.id.rg_sex);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.rb_male){
                    isMale = true;
                    Log.i(TAG,"select male");
                }else {
                    isMale = false;
                    Log.i(TAG,"select female");
                }

            }
        });

    }

    @Override
    protected void iniView() {

    }

    /**
     * 上传编辑的用户信息
     */
    @OnClick(R.id.btn_upload_uer_data)
    public void upLoadUserData(){

        String user_id = tv_user_id.getText().toString();
        String user_name = edt_user_name.getText().toString();
        String user_age = edt_user_age.getText().toString();
        String user_phone_number = edt_phoneNumber.getText().toString();
        String introducer = edt_introducer.getText().toString();
        String address = edt_address.getText().toString();
        String medical_history = edt_medical_history.getText().toString();

        String sex = "男";
        if(!isMale){
            sex = "女";
        }
        editPresenter.upLoadUserData(user_id,user_name,user_age,sex,
                user_phone_number,address,introducer,medical_history);

    }

    @OnClick(R.id.btn_user_id)
    public void getUserId(){
        editPresenter.getUserId();
    }
    @Override
    public void showUserId(String user_id) {
        tv_user_id.setText(user_id);
    }

    @Override
    public void showToast(String msg) {

    }
}