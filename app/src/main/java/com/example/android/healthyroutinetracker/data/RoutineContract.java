package com.example.android.healthyroutinetracker.data;

import android.provider.BaseColumns;

/**
 * Created by JukUm on 6/5/2017.
 */

public final class RoutineContract {

    private RoutineContract() {}

    public static final class RoutineEntry implements BaseColumns {

        //Name of the table
        public final static String TABLE_NAME = "routine";

        //Name of the columns
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_ROUTINE_NAME = "name";
        public final static String COLUMN_ROUTINE_DURATION = "duration";
        public final static String COLUMN_ROUTINE_DATE = "date";

        /**
         * Completion of the routine.
         * Only possible values are ROUTINE_COMPLETED and ROUTINE_NOT_COMPLETED
         * Type: INTEGER
         */
        public final static String COLUMN_ROUTINE_COMPLETED = "completed";

        //Possible values for the routine completion
        public static final int ROUTINE_COMPLETED = 1;
        public static final int ROUTINE_NOT_COMPLETED = 0;

    }
}
