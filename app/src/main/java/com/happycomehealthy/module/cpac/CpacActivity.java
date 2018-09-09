package com.happycomehealthy.module.cpac;

import android.app.AlertDialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.happycomehealthy.R;
import com.happycomehealthy.bean.Constants;
import com.happycomehealthy.module.base.BaseActivity;
import com.happycomehealthy.module.base.BasePresenter;

import com.happycomehealthy.module.test.mydatepicker.DPCManager;
import com.happycomehealthy.module.test.mydatepicker.DPDecor;
import com.happycomehealthy.module.test.mydatepicker.DPMode;
import com.happycomehealthy.module.test.mydatepicker.DataUtils;
import com.happycomehealthy.module.test.mydatepicker.DatePicker;
import com.happycomehealthy.module.test.mydatepicker.DatePicker2;
import com.happycomehealthy.utils.DateUtils;
import com.happycomehealthy.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 顾客个人考勤日历界面
 * Customer attendance calendar
 */
public class CpacActivity extends BaseActivity implements CpacContract.ICpacView{
    private SearchView searchView;
    private Unbinder unbinder;
    private CpacPresenter cpacPresenter;
    @BindView(R.id.my_datepicker)
    DatePicker myDatepicker;
    @BindView(R.id.main_dp)
    DatePicker2 picker;

    @Override
    public int loadLayout() {
        return R.layout.activity_cpac;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this);
        init();
    }

    @Override
    public void initData() {

        String guest_id = getIntent().getStringExtra(Constants.GUEST_ID);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String date = sdf.format(new Date());
        cpacPresenter.getSignDatesInMonth(guest_id,date);





    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    public void updateMonthInfo(int yyyy, int month){
        //         自定义背景绘制示例 Example of custom date's background


//        DatePicker2 picker = (DatePicker2) findViewById(R.id.main_dp);
        picker.setDate(yyyy, month);




    }

    private void init2(){
        DPCManager dpcManager = DPCManager.getInstance();
        dpcManager.clearnDATE_CACHE(); //清除cache
        //自定义背景绘制示例
        List<String> tmp = new ArrayList<>();
        tmp.add("2016-2-3"); //yyyy-M-d
        tmp.add("2016-2-1");
        tmp.add("2016-2-9");
        tmp.add("2016-2-10");
        tmp.add("2016-2-11");
        tmp.add("2016-2-12");
//        dpcManager.setDecorBG(tmp); //预先设置日期背景 一定要在在开始设置

        myDatepicker.setDate(2018, 6); //设置日期

        myDatepicker.setMode(DPMode.NONE); //设置选择模式

        myDatepicker.setFestivalDisplay(false); //是否显示节日
        myDatepicker.setTodayDisplay(false); //是否高亮显示今天
        myDatepicker.setHolidayDisplay(false); //是否显示假期
        myDatepicker.setDeferredDisplay(false); //是否显示补休
        myDatepicker.setIsScroll(false); //是否允许滑动 false表示左右上下都不能滑动  单项设置上下or左右 你需要自己扩展
        myDatepicker.setIsSelChangeColor(true, getResources().getColor(R.color.white)); //设置选择的日期字体颜色,不然有的背景颜色和默认的字体颜色不搭

        myDatepicker.setLeftTitle(DateUtils.getCurrentYear_Month()); //左上方text
        myDatepicker.setRightTitle(false); //是否签到
//        myDatepicker.setOnClickSignIn(this); //点击签到事件

        //设置预先选中日期的背景颜色
        myDatepicker.setDPDecor(new DPDecor() {
            @Override
            public void drawDecorBG(Canvas canvas, Rect rect, Paint paint) {
                paint.setColor(getResources().getColor(R.color.blue));
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 4F, paint);
            }
        });

    }
    private void init() {

        DPCManager dpcManager = DPCManager.getInstance();
        dpcManager.clearnDATE_CACHE(); //清除cache
        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        picker.setMode(DPMode.NONE);
        picker.setDate(year, month);
        picker.setFestivalDisplay(false); //是否显示节日
        picker.setTodayDisplay(false); //是否高亮显示今天
        picker.setHolidayDisplay(false); //是否显示假期
        picker.setDeferredDisplay(false); //是否显示补休
     //   picker.setIsSelChangeColor(true, getResources().getColor(R.color.white)); //设置选择的日期字体颜色,不然有的背景颜色和默认的字体颜色不搭
        picker.setDPDecor(new DPDecor() {
            @Override
            public void drawDecorBG(Canvas canvas, Rect rect, Paint paint) {
                paint.setColor(getResources().getColor(R.color.green));
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 3F, paint);
            }
        });
        picker.setOnDateTouchedListener(new DatePicker2.OnDateTouchedListener() {
            @Override
            public void onDateTouched(String yearMonth) {
                ToastUtils.showToast(yearMonth);
            }
        });




        // 对话框下的DatePicker示例 Example in dialog
        Button btnPick = (Button) findViewById(R.id.main_btn);
        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(CpacActivity.this).create();
                dialog.show();
                DatePicker2 picker = new DatePicker2(CpacActivity.this);
                picker.setDate(2018, 6);
                picker.setMode(DPMode.SINGLE);
                picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
                    @Override
                    public void onDatePicked(String date) {
                        Toast.makeText(CpacActivity.this, date, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setContentView(picker, params);
                dialog.getWindow().setGravity(Gravity.CENTER);
            }
        });
    }
    @Override
    public BasePresenter setPresenter() {
        cpacPresenter = new CpacPresenter(this);

        return cpacPresenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return super.onCreateOptionsMenu(menu);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null)
            unbinder.unbind();
    }

}