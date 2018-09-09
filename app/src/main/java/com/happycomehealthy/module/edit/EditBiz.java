package com.happycomehealthy.module.edit;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.net.ApiHelper;
import com.happycomehealthy.net.pojo.BaseResponse;
import com.happycomehealthy.utils.ToastUtils;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shixinshan on 2018/5/3.
 */

public class EditBiz implements EditContact.IEditBiz{

    private String TAG = EditBiz.class.getSimpleName();
    @Override
    public void upLoadUserData(String user_id, String user_name,
                               String user_age, String sex,
                               String user_phone_number, String address,
                               String introducer, String medical_history,
                               final OperationListener operationListener) {
        if(TextUtils.isEmpty(user_id)){
            ToastUtils.showToast("用户编号没有获取");
            return;
        }
        if (TextUtils.isEmpty(user_name)){

            ToastUtils.showToast("用户名字没有添");
            return;
        }
        if (TextUtils.isEmpty(user_age)){

            ToastUtils.showToast("用户年龄没有添");
        }
        if (TextUtils.isEmpty(user_phone_number)){

            ToastUtils.showToast("用户电话没有添");
        }
        if (TextUtils.isEmpty(address)){

            ToastUtils.showToast("推荐人没有添");
        }
        if (TextUtils.isEmpty(introducer)){

            ToastUtils.showToast("介绍人没有添");
        }
        if (TextUtils.isEmpty(medical_history)){

            ToastUtils.showToast("用户病史没有添");
        }
        Log.i(TAG,"user_id:"+user_id +"\n"+
                "user_name:"+user_name+"\n"+
                "user_sex:"+user_age+"\n"+
                "user_age:"+user_age+"\n"+
                "sex:"+sex+"\n"+
                "user_phone_number:"+user_phone_number+"\n"+
                "address:"+address+"\n"+
                "introducer:"+introducer+"\n"+
                "medical_history:"+medical_history+"\n");


        JsonObject jObj = new JsonObject();
        jObj.addProperty("personId", user_id);
        jObj.addProperty("name", user_name);
        jObj.addProperty("age", user_age);
        jObj.addProperty("sex", sex);
        jObj.addProperty("phoneNumber", user_phone_number);
        jObj.addProperty("address", address);
        jObj.addProperty("introducer", introducer);
        jObj.addProperty("medical_history", medical_history);


        Gson gson = new Gson();
        RequestBody reqBody = RequestBody.create(ApiHelper.mediaType, gson.toJson(jObj));

        Call<BaseResponse> user =
                ApiHelper.getInstance().getApiService().addUsers(reqBody);
        user.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                String code = baseResponse.getCode();
                if(code.equals("ok")){
                    operationListener.onDone(baseResponse.getMessage());
                }else {
                    operationListener.onError(baseResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    public void getPersonId(final OperationListener operationListener) {
        Call<BaseResponse> user =
                ApiHelper.getInstance().getApiService().getGuest();
        user.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                String code = baseResponse.getCode();
                if(code.equals("ok")){
                    operationListener.onDone(baseResponse.getData());
                }else {
                    operationListener.onError(baseResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
