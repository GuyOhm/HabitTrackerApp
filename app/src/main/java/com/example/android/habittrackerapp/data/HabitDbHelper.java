package com.example.android.habittrackerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
