package com.happycomehealthy.module.search.searchguest;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.happycomehealthy.R;
import com.happycomehealthy.module.base.BaseFragment;
import com.happycomehealthy.net.pojo.Guest;
import com.happycomehealthy.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索客户界面
 */
public class SearchGuestFragment extends BaseFragment<SearchGestPrestener> implements SearchGuestContact.ISearchGestView{

    @BindView(R.id.edt_person_id)
    EditText edt_person_id;
    @BindView(R.id.btn_search)
    Button btn_search;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SearchGestPrestener searchGestPrestener;


    public SearchGuestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment SearchGuestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchGuestFragment newInstance() {
        SearchGuestFragment fragment = new SearchGuestFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_guest, container, false);
    }

    @Override
    protected SearchGestPrestener setPresenter() {
        searchGestPrestener = new SearchGestPrestener(this);

        return searchGestPrestener;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void iniView() {

    }


    @OnClick(R.id.btn_search)
    public void getUserInfo(){
        String person_id = edt_person_id.getText().toString();
        if(!TextUtils.isEmpty(person_id)){
            searchGestPrestener.getUserInfo(person_id);
        }else {
            ToastUtils.showToast("请输入用户的编码");
        }
    }


    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showUserId(String user_id) {

    }

    @Override
    public void showUserInfo(Guest guest) {

    }
}
