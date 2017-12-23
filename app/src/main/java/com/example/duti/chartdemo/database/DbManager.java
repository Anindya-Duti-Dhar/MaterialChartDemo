package com.example.duti.chartdemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duti.chartdemo.model.Task;
import com.example.duti.chartdemo.model.TaskPieChart;

import java.util.ArrayList;
import java.util.List;

import static com.example.duti.chartdemo.database.DbHelper.TABLE_ALL_TASK;


public class DbManager {
    private static String TAG = DbManager.class.getSimpleName();

    private static DbManager instance;
    private Context mContext;

    private DbManager(Context context) {
        this.mContext = context;
    }

    public static DbManager getInstance(Context context) {
        if (instance == null) {
            instance = new DbManager(context);
        }

        return instance;
    }

    /* Insert into All task table*/
    public void insertIntoAllGeoTask(String userId, String taskDetails, String taskVenue, String taskAssignedDate, String taskCompletedDate){
        Log.d("local_task","All task table before insert");

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();

        // 1. get reference to writable DB
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        values.put(DbHelper.USER_ID, userId);
        values.put(DbHelper.TASK_DETAILS, taskDetails);
        values.put(DbHelper.TASK_VENUE, taskVenue);
        values.put(DbHelper.TASK_ASSIGNED_DATE, taskAssignedDate);
        values.put(DbHelper.TASK_COMPLETED_DATE, taskCompletedDate);

        // 3. insert
        db.insert(TABLE_ALL_TASK, null, values);
        // 4. close
        dbHelper.close();
        Log.i("local_task", "All task table After insert");
    }

    /* get  data from task table */
    public List<Task> getAllTaskDataFromDB() {
        List<Task> modelList = new ArrayList<Task>();
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "select * from " + TABLE_ALL_TASK;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("local_task", "total number of task: "+cursor.getCount());

        if (cursor.moveToFirst()) {
            do {
                Task model = new Task();
                model.setTaskId(cursor.getString(0));
                model.setUserId(cursor.getString(1));
                model.setTaskDetails(cursor.getString(2));
                model.setTaskVenue(cursor.getString(3));
                model.setTaskAssignedDate(cursor.getString(4));
                model.setTaskCompletedDate(cursor.getString(5));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();
        return modelList;
    }

    /* get  chart data from task table */
    public List<TaskPieChart> getAllTaskChartDataFromDB() {
        List<TaskPieChart> modelList = new ArrayList<TaskPieChart>();
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //String query = "select * from " + TABLE_ALL_TASK;
        String query = "select taskVenue, count (*) from " + TABLE_ALL_TASK + " group by taskVenue";
        Cursor cursor = db.rawQuery(query, null);
        Log.d("local_task", "chart count of task: "+cursor.getCount());

        if (cursor.moveToFirst()) {
            do {
                TaskPieChart model = new TaskPieChart();
                model.setDistrictName(cursor.getString(0));
                model.setCount(Float.parseFloat(cursor.getString(1)));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();
        return modelList;
    }

    /* get  data from Spinner Village table *//*
    public List<VillageSpinner> getSpinnerVillageDataFromDB(String districtCode, String upazilaCode, String unionCode) {
        List<VillageSpinner> modelList = new ArrayList<VillageSpinner>();
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "select * from " + TABLE_PERMITTED_VILLAGE + " where DistrictCode = '"+districtCode+"' and UpazilaCode = '"+upazilaCode+"' and UnionCode = '"+unionCode+"'";
        Cursor cursor = db.rawQuery(query, null);
        Log.d("local_village", "total number of Spinner Village: "+cursor.getCount());

        if (cursor.moveToFirst()) {
            do {
                VillageSpinner model = new VillageSpinner();
                model.setDistrictCode(cursor.getString(0));
                model.setUpazilaCode(cursor.getString(1));
                model.setUnionCode(cursor.getString(2));
                model.setVillageCode(cursor.getString(3));
                model.setVillageName(cursor.getString(4));
                model.setVillageNameBangla(cursor.getString(5));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();
        return modelList;
    }*/

    /*String getNameDistrict(String DistrictCode){
        String name = "";
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ALL_DISTRICT + " where DistrictCode = '"+DistrictCode+"'";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor!=null)
        {
            if(cursor.moveToFirst()) {
                do {
                    name = cursor.getString(cursor.getColumnIndex("DistrictName"));
                } while (cursor.moveToNext());
            }
        }
        return name;
    }
*/
    public void clearDB(String tableName) {
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(tableName, null, null);
        dbHelper.close();
    }

}

