package com.happycomehealthy.module.statistics;

import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.happycomehealthy.bean.CacheData;
import com.happycomehealthy.net.pojo.MonthStatisticBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * BarChart管理类
 * Created by shixinshan on 2018/8/15.
 *
 * 1.得到BarChart对象 并初始化

 2.得到BarEntry对象，此处添加（X，Y）值

 3.得到BarDataSet对象，添加BarEntry对象

 4.得到BarData对象，添加BarDaraSet对象

 5.显示柱状图 BarChart.setData(BarData)
 */

public class BarChartManager {
    private BarChart mBarChart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;

    public BarChartManager(BarChart barChart) {
        this.mBarChart = barChart;
        leftAxis = mBarChart.getAxisLeft();
        rightAxis = mBarChart.getAxisRight();
        xAxis = mBarChart.getXAxis();
    }

    /**
     * 初始化LineChart
     */
    public void initLineChart() {
        //背景颜色
        mBarChart.setBackgroundColor(Color.WHITE);
        //背景阴影
        mBarChart.setDrawBarShadow(false);
        mBarChart.setHighlightFullBarEnabled(false);
        mBarChart.getDescription().setEnabled(false);//设置描述
        mBarChart.setDrawGridBackground(false);// 设置是否绘制背景
        mBarChart.setDrawBorders(true);// 设置是否绘制边框
        mBarChart.setScaleEnabled(true);// 设置是否可以缩放图表
        mBarChart.setDragEnabled(true);// 设置是否可以用手指移动图表

//设置最小的缩放
        mBarChart.setScaleMinima(2f, 1f);//

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn

        // scaling can now only be done on x- and y-axis separately
//        mBarChart.setPinchZoom(false);


        //设置动画效果
        mBarChart.animateY(1000, Easing.EasingOption.Linear);
        mBarChart.animateX(1000, Easing.EasingOption.Linear);

        //折线图例 标签 设置
        Legend legend = mBarChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);//图例样式 (CIRCLE圆形；LINE线性；SQUARE是方块）
        legend.setTextSize(11f);
        //显示位置
        legend.setDrawInside(false);
        //是否显示
        legend.setEnabled(true);
        //图例样式：有圆点，正方形，短线 几种样式
//        legend.setForm(Legend.LegendForm.CIRCLE);
        // 图例显示的位置：如下2行代码设置图例显示在左下角
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        // 图例的排列方式：水平排列和竖直排列2种
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        // 图例距离x轴的距离
        legend.setXEntrySpace(10f);
        //图例距离y轴的距离
        legend.setYEntrySpace(10f);
        //图例的大小
        legend.setFormSize(7f);
        // 图例描述文字大小
        legend.setTextSize(10);

        //X轴的设置
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);// 设置x轴数据的位置
        xAxis.setGranularity(1);//设置x轴最小间隔
        xAxis.setDrawGridLines(false);//不绘制格网线
//        xAxis.setLabelCount(12);//设置x轴显示的标签个数
        xAxis.setAxisLineWidth(1);//设置x轴线的宽度, ...其他样式
//        xAxis.setAxisMaximum(31);
//        xAxis.setSpaceMin(3f);//x轴上第一个刻度点距离原点刻度点距离




        //y轴设置
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//y轴标签绘制的位置
        leftAxis.setDrawGridLines(false);//不绘制y轴格网线
        leftAxis.setGranularity(1);//设置Y轴最小间隔

        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "" + (int) value;//这句是重点!
            }
        });
        rightAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "" + (int) value;//这句是重点!
            }
        });
        //y轴最小
        leftAxis.setDrawZeroLine(false);
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setGranularity(1);//设置Y轴最小间隔




    }

    /**
     * 展示柱状图(一条)
     *
     * @param label
     * @param color
     */
    public void showBarChart( String label, int[] color) {
//        initLineChart();
        //获取某月的统计数据
        List<MonthStatisticBean> monthStatisticBeanList = CacheData.getInstance().getMonthStatisticBeanList();
        if (monthStatisticBeanList == null || monthStatisticBeanList.size() ==0){
            return;
        }
        final List<Float> xAxisValues = new ArrayList<>();
        List<Float> yAxisValues = new ArrayList<>();
        for (int i = 0;i<monthStatisticBeanList.size();i++){
            int day = i;
            xAxisValues.add((float) day);
            MonthStatisticBean monthStatisticBean = monthStatisticBeanList.get(i);
            int count = monthStatisticBean.getCount();
            yAxisValues.add((float) count);

        }
        MonthStatisticBean monthStatisticBean = monthStatisticBeanList.get(0);
        final String[] month = monthStatisticBean.getDate().split("-");


        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < xAxisValues.size(); i++) {
            entries.add(new BarEntry(xAxisValues.get(i), yAxisValues.get(i)));
        }
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                int index = (int) value;
                if (index < 0 || index >= xAxisValues.size()) {
                    return "";
                }
                int day = xAxisValues.get(index).intValue()+1;
                String strDay = String.valueOf(day);
                if(day < 10){
                    strDay = "0"+day;
                }
                return month[1]+ File.separator+strDay;

            }
        });
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, label);
        //设置条形柱状上的数字为整数
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                if(value == 0){
                    return "";
                }
                return (int) value+"人";
            }
        });
        barDataSet.setColors(color);
        barDataSet.setValueTextSize(9);
        barDataSet.setFormLineWidth(1);
        barDataSet.setFormSize(15);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        BarData data = new BarData(dataSets);

        //设置X轴的刻度数“
        xAxis.setLabelCount(xAxisValues.size() - 1, false);
        mBarChart.setData(data);
    }

    /**
     * 展示柱状图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param labels
     * @param colours
     */
    public void showBarChart(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
        initLineChart();
        BarData data = new BarData();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<BarEntry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {

                entries.add(new BarEntry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
            }
            BarDataSet barDataSet = new BarDataSet(entries, labels.get(i));

            barDataSet.setColor(colours.get(i));
            barDataSet.setValueTextColor(colours.get(i));
            barDataSet.setValueTextSize(10f);
            barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            data.addDataSet(barDataSet);
        }
        int amount = yAxisValues.size();

        float groupSpace = 0.12f; //柱状图组之间的间距
        float barSpace = (float) ((1 - 0.12) / amount / 10); // x4 DataSet
        float barWidth = (float) ((1 - 0.12) / amount / 10 * 9); // x4 DataSet

        // (0.2 + 0.02) * 4 + 0.08 = 1.00 -> interval per "group"
        xAxis.setLabelCount(xAxisValues.size() - 1, false);
        data.setBarWidth(barWidth);


        data.groupBars(0, groupSpace, barSpace);
        mBarChart.setData(data);
    }


    /**
     * 设置Y轴值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setYAxis(float max, float min, int labelCount) {
        if (max < min) {
            return;
        }
        leftAxis.setAxisMaximum(max);
        leftAxis.setAxisMinimum(min);
        leftAxis.setLabelCount(labelCount, false);

        rightAxis.setAxisMaximum(max);
        rightAxis.setAxisMinimum(min);
        rightAxis.setLabelCount(labelCount, false);
        mBarChart.invalidate();
    }

    /**
     * 设置X轴的值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setXAxis(float max, float min, int labelCount) {
        xAxis.setAxisMaximum(max);
        xAxis.setAxisMinimum(min);
        xAxis.setLabelCount(labelCount, false);

        mBarChart.invalidate();
    }

    /**
     * 设置高限制线
     *
     * @param high
     * @param name
     */
    public void setHightLimitLine(float high, String name, int color) {
        if (name == null) {
            name = "高限制线";
        }
        LimitLine hightLimit = new LimitLine(high, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
        leftAxis.addLimitLine(hightLimit);
        mBarChart.invalidate();
    }

    /**
     * 设置低限制线
     *
     * @param low
     * @param name
     */
    public void setLowLimitLine(int low, String name) {
        if (name == null) {
            name = "低限制线";
        }
        LimitLine hightLimit = new LimitLine(low, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        leftAxis.addLimitLine(hightLimit);
        mBarChart.invalidate();
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        mBarChart.setDescription(description);
        mBarChart.invalidate();
    }
}
