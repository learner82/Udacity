package com.example.android.habittracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;

import com.example.android.habittracker.data.DbContract;
import com.example.android.habittracker.data.DbHelper;


public class MainActivity extends AppCompatActivity {
    private DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        mDbHelper = new DbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInformation();
    }

    private Cursor displayDatabaseInformation() {

        String[] projection = {
                DbContract.HabitEntry._ID,
                DbContract.HabitEntry.COLUMN_HABIT_NAME,
                DbContract.HabitEntry.COLUMN_HABIT_FREQUENCY,
                DbContract.HabitEntry.COLUMN_HABIT_RATING};

        Cursor cursorOne = mDbHelper.getDetails(projection);

        TextView displayView = (TextView) findViewById(R.id.text_view_habit);

        displayView.setText("The habits table contains " + cursorOne.getCount() + " habits.\n\n");
        displayView.append(DbContract.HabitEntry._ID + " - " +
                DbContract.HabitEntry.COLUMN_HABIT_NAME + " - " +
                DbContract.HabitEntry.COLUMN_HABIT_FREQUENCY + " - " +
                DbContract.HabitEntry.COLUMN_HABIT_RATING + " - " + "\n");

        int idColumnIndex = cursorOne.getColumnIndex(DbContract.HabitEntry._ID);
        int nameColumnIndex = cursorOne.getColumnIndex(DbContract.HabitEntry.COLUMN_HABIT_NAME);
        int frequencyColumnIndex = cursorOne.getColumnIndex(DbContract.HabitEntry.COLUMN_HABIT_FREQUENCY);
        int ratingColumnIndex = cursorOne.getColumnIndex(DbContract.HabitEntry.COLUMN_HABIT_RATING);

        while (cursorOne.moveToNext()) {
            int currentID = cursorOne.getInt(idColumnIndex);
            String currentName = cursorOne.getString(nameColumnIndex);
            int currentFrequency = cursorOne.getInt(frequencyColumnIndex);
            int currentRating = cursorOne.getInt(ratingColumnIndex);

            displayView.append(("\n" + currentID + " - " +
                    currentName + " - " +
                    currentFrequency + " - " +
                    currentRating));
        }
        return cursorOne;
    }
}