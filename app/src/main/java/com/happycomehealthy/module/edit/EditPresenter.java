package com.happycomehealthy.module.edit;

import android.text.TextUtils;
import android.util.Log;

import com.happycomehealthy.bean.Constants;
import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.base.BaseFragment;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.net.ApiHelper;
import com.happycomehealthy.net.pojo.BaseResponse;
import com.happycomehealthy.utils.PreferenceUtils;
import com.happycomehealthy.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shixinshan on 2018/5/3.
 */

public class EditPresenter implements BasePresenter{
    private  EditContact.IEditBiz editBiz;
    private  EditContact.IEditView editFragment;
    private String TAG = EditPresenter.class.getSimpleName();


    public EditPresenter(EditContact.IEditView editFragment) {

        this.editFragment =  editFragment;
        this.editBiz = new EditBiz();
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

    /**
     * 上传编辑的用户信息
     * @param user_id
     * @param user_name
     * @param user_age
     * @param sex
     * @param user_phone_number
     * @param address
     * @param introducer
     * @param medical_history
     */
    public void upLoadUserData(String user_id, String user_name, String user_age, String sex,
                               String user_phone_number, String address,
                               String introducer, String medical_history) {

        editBiz.upLoadUserData(user_id,user_name,user_age, sex,
                user_phone_number, address, introducer,medical_history,
                new OperationListener(){
                    @Override
                    public void onDone(Object obj) {
                        String msg = (String) obj;
                        ToastUtils.showToast("添加成功");
                    }

                    @Override
                    public void onError(String errorMsg) {
                        ToastUtils.showToast(errorMsg);
                    }
                });

    }

    /**
     * 获取用户ID编号
     */
    public void getUserId() {


        editBiz.getPersonId(new OperationListener() {
            @Override
            public void onDone(Object obj) {
                String user_id = (String) obj;
                editFragment.showUserId(user_id);
//                PreferenceUtils.putString(Constants.USER_ID, user_id);
            }

            @Override
            public void onError(String errorMsg) {
                ToastUtils.showToast(errorMsg);
            }
        });


    }
    /**
     * 产生4位随机数(0000-9999)
     * @return 4位随机数
     */
    public static String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++)
                fourRandom = "0" + fourRandom  ;
        }
        return fourRandom;
    }

}
