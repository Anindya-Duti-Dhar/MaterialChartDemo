package com.example.duti.chartdemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    private String TAG = DbHelper.class.getSimpleName();

    private static final String DB_NAME = "scibd_chart";
    public static final int DATABASE_VERSION = 1;

    // table field Information
    public static final String TABLE_ALL_TASK = "all_task";

    public static final String USER_ID = "userId";
    public static final String TASK_ID = "taskId";
    public static final String TASK_ASSIGNED_DATE = "taskAssignedDate";

    public static final String TASK_COMPLETED_DATE = "taskCompletedDate";
    public static final String TASK_DETAILS = "taskDetails";
    public static final String TASK_VENUE = "taskVenue";


    public static final String TABLE_STATEMENT_ALL_TASK = "create table "+TABLE_ALL_TASK +"(taskId INTEGER PRIMARY KEY AUTOINCREMENT, userId TEXT, taskDetails TEXT, taskVenue TEXT, taskAssignedDate TEXT, taskCompletedDate TEXT)";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "--- onCreate database ---");
        // statement for create table
        db.execSQL(TABLE_STATEMENT_ALL_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
