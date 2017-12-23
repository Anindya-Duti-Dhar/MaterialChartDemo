package com.example.duti.chartdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.duti.chartdemo.database.DbManager;
import com.example.duti.chartdemo.model.Task;
import com.example.duti.chartdemo.model.TaskPieChart;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.duti.chartdemo.database.DbHelper.TABLE_ALL_TASK;

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    List<Task> mTaskList = new ArrayList<Task>();
    List<TaskPieChart> mTaskChartList = new ArrayList<TaskPieChart>();
    DbManager helper;
    List<PieEntry> entries = new ArrayList<>();
    PieChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = DbManager.getInstance(MainActivity.this);

        helper.clearDB(TABLE_ALL_TASK);

        helper.insertIntoAllGeoTask("100", "sdfs", "Patuakhali", "dsff", "dfgdfg");
        helper.insertIntoAllGeoTask("100", "sdfs", "Patuakhali", "dsff", "dfgdfg");
        helper.insertIntoAllGeoTask("100", "sdfs", "Barisal", "dsff", "dfgdfg");
        helper.insertIntoAllGeoTask("100", "sdfs", "Patuakhali", "dsff", "dfgdfg");
        helper.insertIntoAllGeoTask("100", "sdfs", "Barisal", "dsff", "dfgdfg");
        helper.insertIntoAllGeoTask("100", "sdfs", "Barguna", "dsff", "dfgdfg");
        helper.insertIntoAllGeoTask("100", "sdfs", "Barguna", "dsff", "dfgdfg");
        helper.insertIntoAllGeoTask("100", "sdfs", "Barisal", "dsff", "dfgdfg");
        helper.insertIntoAllGeoTask("100", "sdfs", "Patuakhali", "dsff", "dfgdfg");
        helper.insertIntoAllGeoTask("100", "sdfs", "Patuakhali", "dsff", "dfgdfg");

        mTaskList.clear();
        mTaskList = helper.getAllTaskDataFromDB();

        mTaskChartList.clear();
        mTaskChartList = helper.getAllTaskChartDataFromDB();
        for(int i = 0; i<mTaskChartList.size(); i++){
            entries.add(new PieEntry(mTaskChartList.get(i).getCount(), mTaskChartList.get(i).getDistrictName()));
        }

        mChart = (PieChart) findViewById(R.id.chart);

        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setDrawEntryLabels(true);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(48f);
        mChart.setTransparentCircleRadius(51f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(MainActivity.this);

        mChart.animateY(2000, Easing.EasingOption.EaseInCirc);
        //mChart.spin(2000, 0, 360, Easing.EasingOption.EaseInOutBounce);


        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTextSize(12f);

        //entries.add(new PieEntry(18.5f, "balagonjo"));
        //entries.add(new PieEntry(26.7f, "kanaighat"));
        //entries.add(new PieEntry(24.0f, "srimongol"));
        //entries.add(new PieEntry(30.8f, "sylhet"));

// select districtName, count (*) from tablename group by districtname

       /* for (Task data : mTaskList) {

            // turn your data into Entry objects
            entries.add(new Entry(data.get(), data.getValueY()));
        }
*/



        Button getDataBtn = (Button)findViewById(R.id.getDataBtn);
        getDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button saveDataBtn = (Button)findViewById(R.id.saveDataBtn);
        saveDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button showChartBtn = (Button)findViewById(R.id.showChartBtn);
        showChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PieDataSet set = new PieDataSet(entries, "Election Results");
                set.setColors(new int[] { R.color.md_red_500, R.color.md_blue_300, R.color.md_yellow_600}, MainActivity.this);
                set.setValueTextColor(R.color.md_white_1000);
                set.setValueLineColor(R.color.md_white_1000);
                PieData data = new PieData(set);
                mChart.setData(data);
                mChart.invalidate(); // refresh
            }
        });

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    /* get  district code from all Geo table */
   /* public void getDistrictCodeFromGeoDB() {
        DbHelper dbHelper = new DbHelper(LoadDataActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "select distinct DistrictCode from " + TABLE_ALL_GEO;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Log.d("permitted_district: ", "count: "+cursor.getString(0));
                if (cursor.getString(0).length() == 1) {
                    getDistrictDataFromDB("0" + cursor.getString(0));
                    getUpazilaCodeFromGeoDB("0" + cursor.getString(0));
                } else {
                    getDistrictDataFromDB(cursor.getString(0));
                    getUpazilaCodeFromGeoDB(cursor.getString(0));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();
    }*/

    /* get  data from all District table */
   /* public void getDistrictDataFromDB(String districtCode) {
        DbHelper dbHelper = new DbHelper(LoadDataActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ALL_DISTRICT + " where DistrictCode = '" + districtCode + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                helper.insertIntoPermittedDistrictDB(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();
    }*/
}
