package com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ThinkPad on 11/7/2017.
 */

public class MySQLHelper extends SQLiteOpenHelper {
    private static final int version = 4;
    private static String databaseName = "Lotus";

    public MySQLHelper(Context context) {
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTable.CREATE_USER);
        db.execSQL(NewsTable.CREATE_NEWS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS ";
        if (newVersion != oldVersion) {
            db.execSQL(drop + UserTable.nameTable_User);
            db.execSQL(drop + NewsTable.nameTable_News);
            onCreate(db);
        }
    }
}
