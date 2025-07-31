package dev.myallworks.native_orm_db;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DaoSupport<T extends BaseModel> {

    private final SQLiteDatabase db;
    private final Class<T> modelClass;

    public DaoSupport(SQLiteDatabase db, Class<T> modelClass) {
        this.db = db;
        this.modelClass = modelClass;
    }

    public List<T> queryAll() {
        List<T> list = new ArrayList<>();
        Cursor cursor = db.query(getTableName(), null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                T model = instantiateModel();
                if (model != null) {
                    try {
                        model.loadFromCursor(cursor);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    list.add(model);
                }
            }
            cursor.close();
        }
        return list;
    }

    public T queryById(long id) {
        Cursor cursor = db.query(
                getTableName(),
                null,
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        T model = null;
        if (cursor != null && cursor.moveToFirst()) {
            model = instantiateModel();
            if (model != null) {
                try {
                    model.loadFromCursor(cursor);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            cursor.close();
        }
        return model;
    }

    private T instantiateModel() {
        try {
            return modelClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getTableName() {
        return modelClass.getSimpleName();
    }
}
