package com.example.android.habittrackerapp.data;

import android.provider.BaseColumns;

/**
 * Database contract for habits tracker app. It contains user and activity tables.
 */

public final class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private HabitContract() {
    }

    /**
     * Inner class that defines the user table contents
     */
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_USER_NAME = "name";
        public static final String COLUMN_USER_DOB = "DOB";
        public static final String COLUMN_USER_EMAIL = "email";
        public static final String COLUMN_USER_GENDER = "gender";

        // possible values for gender
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
    }

    /**
     * Inner class that defines the activity table contents
     */
    public static class ActivityEntry implements BaseColumns {
        public static final String TABLE_NAME = "activity";
        public static final String COLUMN_ACTIVITY_DESC = "description";
        public static final String COLUMN_ACTIVITY_DATE = "date";
        public static final String COLUMN_ACTIVITY_DURATION = "duration"; // in hour
        public static final String COLUMN_ACTIVITY_SATISFACTION = "satisfaction";

        // possible values for satisfaction
        public static final int SATISF_BORING = 0;
        public static final int SATISF_OK = 1;
        public static final int SATISF_AWESOME = 2;
    }
}
