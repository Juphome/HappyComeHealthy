package com.happycomehealthy.module.search.searchguest;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.net.ApiHelper;
import com.happycomehealthy.net.pojo.BaseResponse;
import com.happycomehealthy.net.pojo.Guest;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shixinshan on 2018/5/10.
 */

public class SearchGuestBiz implements SearchGuestContact.ISearchGuestBiz{
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



}
