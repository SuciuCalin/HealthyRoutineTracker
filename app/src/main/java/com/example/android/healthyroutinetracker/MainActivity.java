package com.example.android.healthyroutinetracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.healthyroutinetracker.data.RoutineContract.RoutineEntry;
import com.example.android.healthyroutinetracker.data.RoutineDbHelper;


public class MainActivity extends AppCompatActivity {

    //Database helper that will provide access to the database
    private RoutineDbHelper mDbHelper;

    //String for storing the row values returned by the cursor
    public String cursorLog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new RoutineDbHelper(this);

        insertRoutine();
        readData();

        //Checking the insert with log messages.
        Log.v("MainActivity", cursorLog);

    }

    /**
     * Helper method for inserting hardcoded data into the database.
     */
    public void insertRoutine() {
        //Gets the database in write mode.
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //Creates a new ContentValues object that adds values into the database
        ContentValues values = new ContentValues();
        values.put(RoutineEntry.COLUMN_ROUTINE_NAME, "Working out");
        //Routine duration in minutes
        values.put(RoutineEntry.COLUMN_ROUTINE_DURATION, 60);
        values.put(RoutineEntry.COLUMN_ROUTINE_DATE, "07/06/2017");
        values.put(RoutineEntry.COLUMN_ROUTINE_COMPLETED, RoutineEntry.ROUTINE_COMPLETED);

        //Inserts the new Routine row for "Working out" in the database
        db.insert(RoutineEntry.TABLE_NAME, null, values);
    }

    public Cursor readData() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                RoutineEntry._ID,
                RoutineEntry.COLUMN_ROUTINE_NAME,
                RoutineEntry.COLUMN_ROUTINE_DURATION,
                RoutineEntry.COLUMN_ROUTINE_DATE,
                RoutineEntry.COLUMN_ROUTINE_COMPLETED};

        Cursor cursor = db.query(
                RoutineEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        // Figure out the index of each column
        int idColumnIndex = cursor.getColumnIndex(RoutineEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(RoutineEntry.COLUMN_ROUTINE_NAME);
        int durationColumnIndex = cursor.getColumnIndex(RoutineEntry.COLUMN_ROUTINE_DURATION);
        int dateColumnIndex = cursor.getColumnIndex(RoutineEntry.COLUMN_ROUTINE_DATE);
        int completedColumnIndex = cursor.getColumnIndex(RoutineEntry.COLUMN_ROUTINE_COMPLETED);

        // Iterate through all the returned rows in the cursor
        while (cursor.moveToNext()) {
            // Use that index to extract the String or Int value of the word
            // at the current row the cursor is on.
            int currentID = cursor.getInt(idColumnIndex);
            String currentName = cursor.getString(nameColumnIndex);
            int currentDuration = cursor.getInt(durationColumnIndex);
            String currentDate = cursor.getString(dateColumnIndex);
            int currentCompletion = cursor.getInt(completedColumnIndex);

            //Store the values from each column of the current row in the cursorLog String, to be used with log messages
            cursorLog = "_ID: " + currentID + " | " +
                    "COLUMN_ROUTINE_NAME: " + currentName + " | " +
                    "COLUMN_ROUTINE_DURATION: " + currentDuration + " | " +
                    "COLUMN_ROUTINE_DATE: " + currentDate + " | " +
                    "COLUMN_ROUTINE_COMPLETED: " + currentCompletion;
        }

        cursor.close();
        db.close();
        return cursor;

    }
}