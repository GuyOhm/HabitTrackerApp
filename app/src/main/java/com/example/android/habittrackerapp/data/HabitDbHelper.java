package com.example.android.habittrackerapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.habittrackerapp.data.HabitContract.ActivityEntry;
import com.example.android.habittrackerapp.data.HabitContract.UserEntry;

/**
 * Database helper for Habit tracker app. Manages database creation and version management.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    /**
     * Name of the database file
     */
    private final static String DATABASE_NAME = "habit_tracker.db";

    /**
     * Database version
     */
    private final static int DATABASE_VERSION = 1;

    /**
     * create entries for table user
     */
    private final static String SQL_CREATE_USER_ENTRIES = "CREATE TABLE " + UserEntry.TABLE_NAME + " ("
            + UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UserEntry.COLUMN_USER_NAME + " TEXT NOT NULL, "
            + UserEntry.COLUMN_USER_EMAIL + " TEXT NOT NULL, "
            + UserEntry.COLUMN_USER_DOB + " INTEGER NOT NULL, "
            + UserEntry.COLUMN_USER_GENDER + " INTEGER NOT NULL DEFAULT 0);";

    /**
     * create entries for table activity
     */
    private final static String SQL_CREATE_ACTIVITY_ENTRIES = "CREATE TABLE " + ActivityEntry.TABLE_NAME + " ("
            + ActivityEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ActivityEntry.COLUMN_ACTIVITY_DESC + " TEXT NOT NULL, "
            + ActivityEntry.COLUMN_ACTIVITY_DATE + " TEXT NOT NULL, "
            + ActivityEntry.COLUMN_ACTIVITY_DURATION + " INTEGER NOT NULL, "
            + ActivityEntry.COLUMN_ACTIVITY_SATISFACTION + " INTEGER NOT NULL);";

    /**
     * Constructs a new instance of {@link HabitDbHelper}.
     *
     * @param context of the app
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER_ENTRIES);
        db.execSQL(SQL_CREATE_ACTIVITY_ENTRIES);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Method to insert data in table Activity
     */
    public static void insertActivity(HabitDbHelper dbHelper) {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ActivityEntry.COLUMN_ACTIVITY_DESC, "Coding in Java for Android nanodegree");
        values.put(ActivityEntry.COLUMN_ACTIVITY_DATE, "20/07/2017");
        values.put(ActivityEntry.COLUMN_ACTIVITY_DURATION, "18");
        values.put(ActivityEntry.COLUMN_ACTIVITY_SATISFACTION, ActivityEntry.SATISF_AWESOME);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(HabitContract.ActivityEntry.TABLE_NAME, null, values);
        Log.v("HabitDbHelper", "New row id " + newRowId);
    }

    /**
     * Method to query awesome activities at what date
     */
    public static void readAwesomeActivity(HabitDbHelper dbHelper) {
        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection
        String[] projection = {
                ActivityEntry.COLUMN_ACTIVITY_DESC,
                ActivityEntry.COLUMN_ACTIVITY_DATE};

        // Filter results WHERE "satisfaction" = '2' (awesome)
        String selection = ActivityEntry.COLUMN_ACTIVITY_SATISFACTION + " = ?";
        String[] selectionArgs = {"2"};

        // Perform a query on the activity table
        Cursor cursor = db.query(
                ActivityEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);

        try {
            // Figure out the index of each column
            int descColumnIndex = cursor.getColumnIndex(ActivityEntry.COLUMN_ACTIVITY_DESC);
            int dateColumnIndex = cursor.getColumnIndex(ActivityEntry.COLUMN_ACTIVITY_DATE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String value
                String currentDesc = cursor.getString(descColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                Log.v("HabitDbHelper", "Awesome activities: " + currentDesc + " on " + currentDate);
            }

        } finally {
            // Close the cursor
            cursor.close();
        }
    }

    public static Cursor readAllHabits(HabitDbHelper dbHelper) {
        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection
        String[] projection = {
                ActivityEntry._ID,
                ActivityEntry.COLUMN_ACTIVITY_DESC,
                ActivityEntry.COLUMN_ACTIVITY_DATE,
                ActivityEntry.COLUMN_ACTIVITY_DURATION,
                ActivityEntry.COLUMN_ACTIVITY_SATISFACTION};

        // Perform a query on the activity table
        Cursor cursor = db.query(
                ActivityEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        try {
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(ActivityEntry._ID);
            int descColumnIndex = cursor.getColumnIndex(ActivityEntry.COLUMN_ACTIVITY_DESC);
            int dateColumnIndex = cursor.getColumnIndex(ActivityEntry.COLUMN_ACTIVITY_DATE);
            int durationColumnIndex = cursor.getColumnIndex(ActivityEntry.COLUMN_ACTIVITY_DURATION);
            int satisfactionColumnIndex = cursor.getColumnIndex(ActivityEntry.COLUMN_ACTIVITY_SATISFACTION);


            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String value
                String currentId = cursor.getString(idColumnIndex);
                String currentDesc = cursor.getString(descColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentDur = cursor.getString(durationColumnIndex);
                String currentSatisf = cursor.getString(satisfactionColumnIndex);

                Log.v("HabitDbHelper", currentId + " / " +
                        currentDesc + " / " +
                        currentDate + " / " +
                        currentDur + " / " +
                        currentSatisf);
            }

        } finally {
            // Close the cursor
            cursor.close();
        }

        return cursor;
    }
}
