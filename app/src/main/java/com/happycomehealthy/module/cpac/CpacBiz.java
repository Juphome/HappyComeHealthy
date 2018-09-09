package com.happycomehealthy.module.cpac;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.net.ApiHelper;
import com.happycomehealthy.net.pojo.BaseResponse;
import com.happycomehealthy.net.pojo.Guest;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shixinshan on 2018/6/3.
 */

public class CpacBiz implements CpacContract.ICpacBiz {

    private String TAG = CpacBiz.class.getSimpleName();
    @Override
    public void getSignDatesInMonth(String guest_id, String yearMonth, final OperationListener operationListener) {
        Logger.i("guest_id = "+guest_id + "yearMonth = "+ yearMonth);
        Call<BaseResponse<String[]>> guest =
                ApiHelper.getInstance().getApiService().getSignDatesInMonth(guest_id,yearMonth);
        guest.enqueue(new Callback<BaseResponse<String[]>>() {
            @Override
            public void onResponse(Call<BaseResponse<String[]>> call, Response<BaseResponse<String[]>> response) {
                BaseResponse body = response.body();
                if(body == null){
                    operationListener.onError("后台返回数据空");
                    return;
                }
                String code = body.getCode();
                if(code.equals("ok")){
                    String[] data = (String[]) body.getData();
                    operationListener.onDone(data);

                }else {
                    operationListener.onError(body.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<String[]>> call, Throwable t) {
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
