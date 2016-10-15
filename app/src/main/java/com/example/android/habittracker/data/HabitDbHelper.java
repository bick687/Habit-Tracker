package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by jitso on 10/14/2016.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    //Log tag for the log messages
    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    //Database version number
    private static final int DATABASE_VERSION = 1;
    //Name for the database file
    private static final String HABITS_DATABASE_NAME = "habits.db";

    //Database constructor
    public HabitDbHelper(Context context) {
        super(context, HABITS_DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a String that contains the SQL statement to create the habits database
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_NAME + " STRING NOT NULL, "
                + HabitEntry.COLUMN_HABIT_STARTTIME + " STRING NOT NULL, "
                + HabitEntry.COLUMN_HABIT_HOURS + " INTEGER NOT NULL, "
                + HabitEntry.COLUMN_HABIT_DAYS + " STRING NOT NULL );";
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    //There is no version update required since there are no changes to the database
    }
}
