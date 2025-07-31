package dev.myallworks.native_orm_db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseModel {

    public long save() {
        SQLiteDatabase db = OrmHelper.getInstance(NativeOrmDb.getContext()).getWritableDatabase();
        ContentValues values = toContentValues();
        return db.insert(getTableName(), null, values);
    }

    public static <T extends BaseModel> List<T> findAll(Class<T> modelClass) {
        List<T> list = new ArrayList<>();
        SQLiteDatabase db = OrmHelper.getInstance(NativeOrmDb.getContext()).getReadableDatabase();
        Cursor cursor = db.query(getTableName(modelClass), null, null, null, null, null, null);
        try {
            while (cursor.moveToNext()) {
                T model = modelClass.newInstance();
                model.loadFromCursor(cursor);
                list.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return list;
    }

    private void loadFromCursor(Cursor cursor) throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            int columnIndex = cursor.getColumnIndex(field.getName());
            if (columnIndex != -1) {
                Object value = getValueFromCursor(cursor, columnIndex, field.getType());
                field.set(this, value);
            }
        }
    }

    private Object getValueFromCursor(Cursor cursor, int columnIndex, Class<?> type) {
        if (type == String.class) return cursor.getString(columnIndex);
        if (type == int.class || type == Integer.class) return cursor.getInt(columnIndex);
        if (type == long.class || type == Long.class) return cursor.getLong(columnIndex);
        if (type == float.class || type == Float.class) return cursor.getFloat(columnIndex);
        if (type == double.class || type == Double.class) return cursor.getDouble(columnIndex);
        return null;
    }

    private ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object val = field.get(this);
                if (val instanceof String) values.put(field.getName(), (String) val);
                else if (val instanceof Integer) values.put(field.getName(), (Integer) val);
                else if (val instanceof Long) values.put(field.getName(), (Long) val);
                else if (val instanceof Float) values.put(field.getName(), (Float) val);
                else if (val instanceof Double) values.put(field.getName(), (Double) val);
            } catch (IllegalAccessException ignored) {}
        }
        return values;
    }

    private static <T> String getTableName(Class<T> cls) {
        return cls.getSimpleName().toLowerCase();
    }

    private String getTableName() {
        return getClass().getSimpleName().toLowerCase();
    }
}

