package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by jitso on 10/14/2016.
 */

public class HabitContract {

    //Log tag for the log messages
    public static final String LOG_TAG = HabitContract.class.getSimpleName();

    //Define the constructor
    //An empty private constructor makes sure that the class is not going to be initialised.
    private HabitContract() {
    }

    //Implement the BaseColumns method for initializing database columns
    public static final class HabitEntry implements BaseColumns {

        //Create a table to store the habits information
        public static final String TABLE_NAME = "habits";

        //Define the column names:
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_STARTTIME = "starttime";
        public static final String COLUMN_HABIT_HOURS = "hours";
        public static final String COLUMN_HABIT_DAYS = "days";
    }
}
