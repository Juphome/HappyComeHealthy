package com.happycomehealthy.module.main;

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
 * Created by shixinshan on 2018/6/1.
 */

public class MainBiz implements MainContract.IMainBiz {
    @Override
    public void getGuest(String person_id, final OperationListener operationListener) {
        Call<BaseResponse<Guest>> guest =
                ApiHelper.getInstance().getApiService().getGuest(person_id);
        guest.enqueue(new Callback<BaseResponse<Guest>>() {
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
                } else if(t instanceof ConnectException){
                    operationListener.onError("连接失败");
                }else {

                    operationListener.onError("未知错误");

                }
            }
        });
    }


}
