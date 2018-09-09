package com.happycomehealthy.module.statistics;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
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
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.happycomehealthy.bean.CacheData;
import com.happycomehealthy.net.pojo.MonthStatisticBean;
import com.happycomehealthy.widget.LineChartMarkView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * LineChart管理类
 * Created by shixinshan on 2018/8/15.
 */

public class LineChartManager {
    private LineChart lineChart;
    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYaxis;           //右侧Y轴
    private Legend legend;              //图例
    private LimitLine limitLine;        //限制线

    public LineChartManager(LineChart lineChart) {
        this.lineChart = lineChart;
        /***图表设置***/
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(true);
        //是否可以拖动
        lineChart.setDragEnabled(true);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        lineChart.setScaleMinima(2.5f, 1f);//

        //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);

        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);

        leftYAxis.setDrawGridLines(false);//不绘制y轴格网线
        rightYaxis.setDrawGridLines(false);

        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        rightYaxis.setAxisMinimum(0f);
        leftYAxis.setGranularity(1);
        rightYaxis.setGranularity(1);
        leftYAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "" + (int) value;//这句是重点!
            }
        });
        rightYaxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "" + (int) value;//这句是重点!
            }
        });

        /***折线图例 标签 设置***/
        legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
    }

    /**
     * 曲线初始化设置 一个LineDataSet 代表一条曲线
     *
     * @param lineDataSet 线条
     * @param color       线条颜色
     * @param mode
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }


    /**
     * 展示曲线
     *
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public void showLineChart( String name, int color) {
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


        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < xAxisValues.size(); i++) {
            entries.add(new Entry(xAxisValues.get(i), yAxisValues.get(i)));
        }
//        List<Entry> entries = new ArrayList<>();
//        for (int i = 0; i < dataList.size(); i++) {
//            IncomeBean data = dataList.get(i);
//            /**
//             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
//             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
//             */
//            Entry entry = new Entry(i, (float) data.getValue());
//            entries.add(entry);
//        }
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
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        lineDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                if(value == 0){
                    return "";
                }
                return (int) value+"人";
            }
        });
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.CUBIC_BEZIER);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }


    /**
     * 设置 可以显示X Y 轴自定义值的 MarkerView
     */
    public void setMarkerView(Context context) {
        LineChartMarkView mv = new LineChartMarkView(context, xAxis.getValueFormatter());
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);
        lineChart.invalidate();
    }

}
