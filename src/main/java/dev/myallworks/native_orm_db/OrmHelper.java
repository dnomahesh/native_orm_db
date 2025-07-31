package dev.myallworks.native_orm_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrmHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "devorm.db";
    private static final int DB_VERSION = 1;

    private static OrmHelper instance;

    public static synchronized OrmHelper getInstance(Context context) {
        if (instance == null) {
            instance = new OrmHelper(context.getApplicationContext());
        }
        return instance;
    }

    private OrmHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tables should be created by your models
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Migration logic
    }
}
