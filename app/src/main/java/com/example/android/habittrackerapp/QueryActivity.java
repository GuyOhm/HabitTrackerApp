package com.example.android.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.android.habittrackerapp.data.HabitContract;
import com.example.android.habittrackerapp.data.HabitContract.ActivityEntry;
import com.example.android.habittrackerapp.data.HabitDbHelper;

/**
 * Contains queries to insert and read from habit_tracker database
 */
public class QueryActivity extends AppCompatActivity {

    /**
     * Declare a {@link HabitDbHelper} object
     */
    private HabitDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate a HabitDbHelper object to communicate with the database
        dbHelper = new HabitDbHelper(this);

    }

    /**
     * Method to insert data in table Activity
     */
    private void insertActivity() {
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
        Toast.makeText(this, "new row id: " + newRowId, Toast.LENGTH_LONG).show();
    }

    /**
     * Method to query awesome activities at what date
     */
    private void readAwesomeActivity() {
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
                Log.v("QueryActivity", "Awesome activities: " + currentDesc + " on " + currentDate);
            }

        } finally {
            // Close the cursor
            cursor.close();
        }

    }

}
