package com.happycomehealthy.module.sign;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.net.ApiHelper;
import com.happycomehealthy.net.pojo.BaseResponse;
import com.happycomehealthy.net.pojo.Guest;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shixinshan on 2018/5/10.
 */

public class SignBiz implements SignContact.ISignBiz{
    @Override
    public void getUser(String person_id, final OperationListener operationListener) {
        Call<BaseResponse<Guest>> user =
                ApiHelper.getInstance().getApiService().getGuest(person_id);
        user.enqueue(new Callback<BaseResponse<Guest>>() {
            @Override
            public void onResponse(Call<BaseResponse<Guest>> call, Response<BaseResponse<Guest>> response) {
                BaseResponse body = response.body();
                if(body == null){
                    operationListener.onError("后台返回数据空");
                    return;
                }
                String code = body.getCode();
                if(code.equals("ok")){
                    Guest data = (Guest) body.getData();
                    operationListener.onDone(data);

                }else {
                    operationListener.onError(body.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Guest>> call, Throwable t) {
                t.printStackTrace();
                if(t instanceof SocketTimeoutException){

                    operationListener.onError("连接超时");
                }else if(t instanceof ConnectException){
                    operationListener.onError("连接失败");
                }else {

                    operationListener.onError("未知错误");

                }
            }
        });
    }

    @Override
    public void sign(String user_id, String user_name, String sex, String age,
                     String medical_history, final OperationListener operationListener) {
        JsonObject jObj = new JsonObject();
        jObj.addProperty("personId", user_id);
        jObj.addProperty("name", user_name);
        jObj.addProperty("age", age);
        jObj.addProperty("sex", sex);
        jObj.addProperty("medical_history", medical_history);

        long current = System.currentTimeMillis();
        jObj.addProperty("signDate",current);

        Gson gson = new Gson();
        RequestBody reqBody = RequestBody.create(ApiHelper.mediaType, gson.toJson(jObj));
        Call<BaseResponse> user =
                ApiHelper.getInstance().getApiService().sign(reqBody);
        user.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse body = response.body();
                if(body == null){
                    operationListener.onError("后台返回数据空");
                    return;
                }
                String code = body.getCode();
                if(!TextUtils.isEmpty(code) && code.equals("ok")){
                    operationListener.onDone(body.getMessage());

                }else {
                    operationListener.onError(body.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                t.printStackTrace();
                if(t instanceof SocketTimeoutException){

                    operationListener.onError("连接超时");
                }else if(t instanceof ConnectException){
                    operationListener.onError("连接失败");
                }else {

                    operationListener.onError("未知错误");

                }
            }
        });
    }
}
