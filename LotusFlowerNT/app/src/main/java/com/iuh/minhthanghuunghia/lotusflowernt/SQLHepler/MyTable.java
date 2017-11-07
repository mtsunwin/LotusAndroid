package com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ThinkPad on 11/7/2017.
 */

public abstract class MyTable {
    public SQLiteDatabase database;

    public MyTable(Context context) {
        MySQLHelper helper = new MySQLHelper(context);
        database = helper.getReadableDatabase();
    }

    public abstract long insert(Object _obj);

    public abstract long update(Object _obj);

    public abstract long delete(int id);
}
