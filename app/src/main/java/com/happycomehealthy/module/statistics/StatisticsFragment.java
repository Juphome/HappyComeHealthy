package com.happycomehealthy.module.statistics;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Toast;

import com.happycomehealthy.R;
import com.happycomehealthy.module.base.BaseFragment;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.statistics.day.DayStatisticActivity;
import com.happycomehealthy.module.statistics.month.MonthStatisticActivity;
import com.happycomehealthy.module.test.mydatepicker.DPMode;
import com.happycomehealthy.module.test.mydatepicker.DatePicker;
import com.happycomehealthy.module.test.mydatepicker.DatePicker2;
import com.happycomehealthy.utils.ToastUtils;
import com.happycomehealthy.widget.TextItemVew;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 统计
 * Created by shixinshan on 2018/5/3.
 */

public class StatisticsFragment extends BaseFragment implements StatisticsContact.IStatisticsView{


    @BindView(R.id.tiv_day_statistic)
    TextItemVew tiv_day_statistic;

    @BindView(R.id.tiv_month_statistic)
    TextItemVew tiv_month_statistic;

    @BindView(R.id.tiv_year_statistic)
    TextItemVew tiv_year_statistic;
    private StatisticsPresenter statisticsPresenter;

    public static StatisticsFragment newInstance(String param1) {
        StatisticsFragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public StatisticsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    protected BasePresenter setPresenter() {
        statisticsPresenter = new StatisticsPresenter(this);

        return statisticsPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_statistics;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void iniView() {

    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Revenues\nQuarters 2015");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }


    @Override
    public void showToast(String msg) {

    }

    /**
     * 获取月统计数据
     */
    @OnClick(R.id.tiv_month_statistic)
    public void getMonthStatisticData(){
        // 弹出月份选择的控件
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.show();
        DatePicker2 picker = new DatePicker2(getActivity());
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        picker.setDate(year, month);
        picker.setMode(DPMode.SINGLE);
        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                Toast.makeText(getActivity(), date, Toast.LENGTH_LONG).show();
                dialog.dismiss();
                statisticsPresenter.getMonthStatisticData(date);
            }
        });
        picker.setOnDateTouchedListener(new DatePicker2.OnDateTouchedListener() {
            @Override
            public void onDateTouched(String yearMonth) {
                ToastUtils.showToast(yearMonth);
            }
        });
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setContentView(picker, params);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    @Override
    public void showMonthStatistic() {
        Intent intent = new Intent(getActivity(), MonthStatisticActivity.class);
        startActivity(intent);
    }

    @Override
    public void showDayStatistic() {
        Intent intent = new Intent(getActivity(), DayStatisticActivity.class);
        startActivity(intent);
    }

    /**
     * 获取月统计数据
     */
    @OnClick(R.id.tiv_day_statistic)
    public void getDaytatisticData(){
        // 弹出月份选择的控件
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.show();
        DatePicker2 picker = new DatePicker2(getActivity());
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        picker.setDate(year, month);
        picker.setMode(DPMode.SINGLE);
        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                Toast.makeText(getActivity(), date, Toast.LENGTH_LONG).show();
                dialog.dismiss();
                statisticsPresenter.getDayStatisticData(date);
            }
        });
        picker.setOnDateTouchedListener(new DatePicker2.OnDateTouchedListener() {
            @Override
            public void onDateTouched(String yearMonth) {
                ToastUtils.showToast(yearMonth);
            }
        });
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setContentView(picker, params);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }
}
