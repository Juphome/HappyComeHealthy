package com.happycomehealthy.module.cpac;

import android.os.Handler;
import android.text.TextUtils;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.search.SearchContract;
import com.happycomehealthy.module.test.mydatepicker.DPCManager;
import com.happycomehealthy.utils.DateUtils;
import com.happycomehealthy.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 客人签到日历控制器
 * Created by shixinshan on 2018/5/29.
 */

public class CpacPresenter implements CpacContract.ICpacPresenter{


    private  CpacContract.ICpacView iView;
    private  CpacBiz iBiz;

    public CpacPresenter(CpacContract.ICpacView cpacActivity) {
        this.iView = cpacActivity;
        this.iBiz = new CpacBiz();
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


    @Override
    public void getSignDatesInMonth(String guest_id, String todayDate) {
        if(TextUtils.isEmpty(guest_id)){
            ToastUtils.showToast("客人ID为空");
            return;
        }
        if(TextUtils.isEmpty(todayDate)){
            ToastUtils.showToast("获取当天日期为空");
            return;
        }

        iBiz.getSignDatesInMonth(guest_id,todayDate,new OperationListener(){
            @Override
            public void onDone(Object obj) {

                String[] dates = (String[]) obj;
                updateSignMonthView(dates);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

    /**
     * 更新日历表，某月的签到数据
     * @param dates
     */
    private void updateSignMonthView(String[] dates) {
        if(dates == null || dates.length == 0){
            ToastUtils.showToast("后台没有给签到的数据");
            return;
        }
        List<String> tmp = new ArrayList<>();
        for(int i = 0 ;i<dates.length;i++){
            String date = dates[i];
            String stampToDate = DateUtils.UTCStringtODefaulString3(date);
            tmp.add(stampToDate);
            Logger.i("stampToDate="+stampToDate);
        }
        DPCManager.getInstance().clearnDATE_CACHE(); //清除cache
        DPCManager.getInstance().setDecorBG(tmp);
        try {
            String[] yyyy_month = tmp.get(0).split("-");
            int yyyy = Integer.parseInt(yyyy_month[0]);
            int month = Integer.parseInt(yyyy_month[1]);

            iView.updateMonthInfo(yyyy, month);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
