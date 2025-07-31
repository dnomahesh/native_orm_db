package dev.myallworks.native_orm_db;

import android.content.Context;

public class NativeOrmDb {
        private static Context context;

        public static void initialize(Context ctx) {
            context = ctx.getApplicationContext();
            OrmHelper.getInstance(context).getWritableDatabase();
        }

        public static Context getContext() {
            return context;
        }
    }
