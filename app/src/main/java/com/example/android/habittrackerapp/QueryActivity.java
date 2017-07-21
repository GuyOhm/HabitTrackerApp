package com.example.android.habittrackerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.habittrackerapp.data.HabitDbHelper;

/**
 * Contains queries to insert and read from habit_tracker database
 */
public class QueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate a HabitDbHelper object to communicate with database
        HabitDbHelper dbHelper = new HabitDbHelper(this);

        // test methods
        HabitDbHelper.insertActivity(dbHelper);
        HabitDbHelper.insertActivity(dbHelper);
        HabitDbHelper.insertActivity(dbHelper);
        HabitDbHelper.readAwesomeActivity(dbHelper);
        HabitDbHelper.readAllHabits(dbHelper);


    }
}
