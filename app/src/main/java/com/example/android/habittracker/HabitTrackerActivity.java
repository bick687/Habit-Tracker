package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

public class HabitTrackerActivity extends AppCompatActivity {

    //Log tag for the log messages
    public static final String LOG_TAG = HabitTrackerActivity.class.getSimpleName();

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_tracker);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDbHelper(this);

        //Call Method to insert data into table
        insertHabit();

        //Call Method to display the information read from the database
        displayDatabaseInfo();
    }

    private Cursor displayDatabaseInfo() {
        //Create and/or open a database to read it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //Declare what columns need to be read
        String[] projection = {HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_STARTTIME,
                HabitEntry.COLUMN_HABIT_HOURS,
                HabitEntry.COLUMN_HABIT_DAYS};

        //Apply a cursor on the database in order to start reading the table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME, // The table to query
                projection,          // The columns to return
                null,                // The columns for the WHERE clause
                null,                // The values for the WHERE clause
                null,                // Don't group the rows
                null,                // Don't filter by row groups
                null);               // The sort order

        try {
            //Find out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int timeColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_STARTTIME);
            int hoursColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_HOURS);
            int daysColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DAYS);

            //Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                //Use the index to extract the String or Int value of the word
                //at the current row
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentTime = cursor.getString(timeColumnIndex);
                int currentHours = cursor.getInt(hoursColumnIndex);
                String currentDays = cursor.getString(daysColumnIndex);
            }
        } finally {
            //Once done with reading from cursor, close it.
            cursor.close();
        }

        return cursor;
    }

    private void insertHabit() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //Create a new row to add habit
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Morning Walk");
        values.put(HabitEntry.COLUMN_HABIT_STARTTIME, "5:00 AM");
        values.put(HabitEntry.COLUMN_HABIT_HOURS, 1);
        values.put(HabitEntry.COLUMN_HABIT_DAYS, "Weekdays");

        //Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }
}
