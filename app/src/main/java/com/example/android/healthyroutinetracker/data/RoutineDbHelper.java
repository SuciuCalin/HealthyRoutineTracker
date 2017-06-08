package com.example.android.healthyroutinetracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.healthyroutinetracker.data.RoutineContract.RoutineEntry;

/**
 * Created by JukUm on 6/8/2017.
 */


/**
 * Database helper for the Routine app. Manages database creation and version management.
 */
public class RoutineDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;             //Database version. Must be incremented on schema change.
    public static final String DATABASE_NAME = "routine.db";  //Name of the database file.

    private SQLiteDatabase db;


    /**
     * Constructs a new instance of {@link RoutineDbHelper}.
     *
     * @param context is the context of the app.
     */
    public RoutineDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    /**
     * Called when the database is created for the first time.
     * Creates a String that contains the SQL statement for table creation.
     * Executes the SQL statement.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ROUTINE_TABLE = "CREATE TABLE IF NOT EXISTS "
                + RoutineEntry.TABLE_NAME + "("
                + RoutineEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + RoutineEntry.COLUMN_ROUTINE_NAME + " TEXT NOT NULL,"
                + RoutineEntry.COLUMN_ROUTINE_DURATION + " INTEGER NOT NULL DEFAULT 0,"
                + RoutineEntry.COLUMN_ROUTINE_DATE + " TEXT NOT NULL,"
                + RoutineEntry.COLUMN_ROUTINE_COMPLETED + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_ROUTINE_TABLE);
    }

    /**
     * Called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES = "DELETE TABLE IF EXISTS " + RoutineEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
