package com.happycomehealthy.module.statistics.month;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.happycomehealthy.R;
import com.happycomehealthy.module.statistics.BarChartManager;
import com.happycomehealthy.module.statistics.DemoBase;
import com.happycomehealthy.module.statistics.LineChartManager;

public class MonthStatisticActivity extends DemoBase  {

    private BarChart barChart;
    private LineChart mLineChart;
    private String TAG = MonthStatisticActivity.class.getSimpleName();
    private BarChartManager barChartManager;
    private LineChartManager lineChartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_month_statistic);

        barChart = findViewById(R.id.bar_char);
        mLineChart = findViewById(R.id.line_char);

        barChartManager = new BarChartManager(barChart);
        barChartManager.initLineChart();

        lineChartManager = new LineChartManager(mLineChart);
        lineChartManager.setMarkerView(this);
        onProgressChanged();
        // add a nice and smooth animation

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionToggleValues: {

                for (IDataSet set : barChart.getData().getDataSets())
                    set.setDrawValues(!set.isDrawValuesEnabled());

                barChart.invalidate();
                break;
            }
            case R.id.actionToggleHighlight: {

                if(barChart.getData() != null) {
                    barChart.getData().setHighlightEnabled(!barChart.getData().isHighlightEnabled());
                    barChart.invalidate();
                }
                break;
            }
            case R.id.actionTogglePinch: {
                if (barChart.isPinchZoomEnabled())
                    barChart.setPinchZoom(false);
                else
                    barChart.setPinchZoom(true);

                barChart.invalidate();
                break;
            }
            case R.id.actionToggleAutoScaleMinMax: {
                barChart.setAutoScaleMinMaxEnabled(!barChart.isAutoScaleMinMaxEnabled());
                barChart.notifyDataSetChanged();
                break;
            }
            case R.id.actionToggleBarBorders: {
                for (IBarDataSet set : barChart.getData().getDataSets())
                    ((BarDataSet)set).setBarBorderWidth(set.getBarBorderWidth() == 1.f ? 0.f : 1.f);

                barChart.invalidate();
                break;
            }
            case R.id.animateX: {
                barChart.animateX(3000);
                break;
            }
            case R.id.animateY: {
                barChart.animateY(3000);
                break;
            }
            case R.id.animateXY: {

                barChart.animateXY(3000, 3000);
                break;
            }
            case R.id.actionSave: {
                if (barChart.saveToGallery("title" + System.currentTimeMillis(), 50)) {
                    Toast.makeText(getApplicationContext(), "Saving SUCCESSFUL!",
                            Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Saving FAILED!", Toast.LENGTH_SHORT)
                            .show();
                break;
            }
        }
        return true;

        //
    }

    public void onProgressChanged() {


        barChartManager.showBarChart("8月统计",ColorTemplate.VORDIPLOM_COLORS);
        barChart.invalidate();

        lineChartManager.showLineChart("8月统计", Color.CYAN);

    }


}
