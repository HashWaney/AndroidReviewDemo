package com.hash.sqlitedemo.sqlite;

import android.provider.BaseColumns;

/**
 * Created by HashWaney on 2019/3/17.
 */

public class FeedReaderContract {

    private FeedReaderContract() {

    }


    public static class FeedEntry implements
            BaseColumns {

        public static final String TABLE_NAME = "person";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_PERSON_NAME = "person_name";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_PERSON_ID = "person_id";

    }

    //p.getPersonId(), p.getAge(),
//                            p.getPersonName(), p.getCity()
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME
                    + " (" + FeedEntry._ID + " INTEGER PRIMARY KEY, "
                    + FeedEntry.COLUMN_NAME_PERSON_ID + " TEXT, "
                    + FeedEntry.COLUMN_NAME_AGE + " INTEGER NOT NULL DEFAULT 0, "
                    + FeedEntry.COLUMN_NAME_PERSON_NAME + " TEXT, "
                    + FeedEntry.COLUMN_NAME_CITY + " TEXT)";


    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


}
