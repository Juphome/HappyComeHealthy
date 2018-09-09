package com.happycomehealthy.module.statistics;

import android.widget.LinearLayout;

import com.happycomehealthy.bean.DayGuest;
import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.net.ApiHelper;
import com.happycomehealthy.net.pojo.BaseResponse;
import com.happycomehealthy.net.pojo.MonthStatisticBean;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shixinshan on 2018/6/19.
 */

public class StatisticsBiz implements StatisticsContact.IStatisticsBiz {
    @Override
    public void getMonthStatisticsData(String month, final OperationListener<List<MonthStatisticBean>> operationListener) {
        Call<BaseResponse<List<MonthStatisticBean>>> user =
                ApiHelper.getInstance().getApiService().getMonthStatisticsData(month);
        user.enqueue(new Callback<BaseResponse<List<MonthStatisticBean>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<MonthStatisticBean>>> call, Response<BaseResponse<List<MonthStatisticBean>>> response) {
                BaseResponse body = response.body();
                if(body == null){
                    operationListener.onError("后台返回数据空");
                    return;
                }
                String code = body.getCode();
                if(code.equals("ok")){
                    List<MonthStatisticBean> data = (List<MonthStatisticBean>) body.getData();
                    operationListener.onDone(data);

                }else {
                    operationListener.onError(body.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<MonthStatisticBean>>> call, Throwable t) {
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
    public void getDayStatisticsData(String day, final OperationListener<List<DayGuest>> operationListener) {

        Call<BaseResponse<List<DayGuest>>> user =
                ApiHelper.getInstance().getApiService().getDayStatisticsData(day);
        user.enqueue(new Callback<BaseResponse<List<DayGuest>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<DayGuest>>> call, Response<BaseResponse<List<DayGuest>>> response) {
                BaseResponse body = response.body();
                if(body == null){
                    operationListener.onError("后台返回数据空");
                    return;
                }
                String code = body.getCode();
                if(code.equals("ok")){
                    List<DayGuest> data = (List<DayGuest>) body.getData();
                    operationListener.onDone(data);

                }else {
                    operationListener.onError(body.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<DayGuest>>> call, Throwable t) {
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
