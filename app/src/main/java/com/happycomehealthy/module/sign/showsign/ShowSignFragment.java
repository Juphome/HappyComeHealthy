package com.happycomehealthy.module.sign.showsign;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.happycomehealthy.R;
import com.happycomehealthy.net.pojo.Guest;
import com.happycomehealthy.widget.TextItemVew;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */
public class ShowSignFragment extends Fragment {



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
    @BindView(R.id.edt_address)
    EditText edt_address;

    @BindView(R.id.tiv_introducer)
    TextItemVew tiv_introducer;

    @BindView(R.id.edt_medical_history)
    EditText edt_medical_history;


    @BindView(R.id.btn_sign)
    Button btn_sign;

    @BindView(R.id.btn_edit)
    Button btn_edit;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ShowSignFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowSignFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowSignFragment newInstance(String param1, String param2) {
        ShowSignFragment fragment = new ShowSignFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_sign, container, false);
    }



    public void showUserInfo(Guest guest) {

        tiv_user_id.getTv_right_text().setText(guest.getPersonId());
        tiv_user_name.getTv_right_text().setText(guest.getName());
        tiv_phoneNumber.getTv_right_text().setText(guest.getPhoneNumber());
        tiv_age.getTv_right_text().setText(guest.getAge());
        edt_address.setText(guest.getAddress());
        tiv_introducer.getTv_right_text().setText(guest.getIntroducer());
        edt_medical_history.setText(guest.getMedical_history());
        tiv_sex.getTv_right_text().setText(guest.getSex());

        if(guest.isSigned()){
            btn_sign.setEnabled(false);
            btn_sign.setText(R.string.has_signed);
            btn_sign.setBackgroundColor(Color.parseColor("#00CC33"));
        }else {
            btn_sign.setEnabled(true);
            btn_sign.setText(R.string.has_not_signed);
            btn_sign.setBackgroundColor(Color.parseColor("#FC9D04"));
        }

    }


    @OnClick(R.id.btn_sign)
    public void sign(){
        String user_id = tiv_user_id.getTv_right_text().getText().toString();
        String user_name = tiv_user_name.getTv_right_text().getText().toString();
        String sex = tiv_sex.getTv_right_text().getText().toString();
        String age = tiv_age.getTv_right_text().getText().toString();
        String medical_history = edt_medical_history.getText().toString();

//        signPresenter.sign(user_id,user_name,sex,age,medical_history);
    }


}
