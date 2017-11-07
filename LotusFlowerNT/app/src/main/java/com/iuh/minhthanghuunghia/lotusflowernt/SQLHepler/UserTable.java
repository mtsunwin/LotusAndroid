package com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.iuh.minhthanghuunghia.lotusflowernt.Model.User;

/**
 * Created by ThinkPad on 11/7/2017.
 */

public class UserTable extends MyTable {
    public static final String nameTable_User = "User";
    public static final String col_username = "username";
    public static final String col_password = "password";
    public static final String col_email = "email";
    public static final String col_nickname = "nickname";
    public static final String col_address = "address";
    public static final String col_gender = "gender";
    public static final String col_about = "about";
    public static final String col_id = "id";

    public static final String CREATE_USER = "CREATE TABLE " + nameTable_User + "("
            + col_id + " integer primary key autoincrement, "
            + col_username + " nvarchar(50) not null, "
            + col_password + " nvarchar(50) not null, "
            + col_email + " nvarchar(50) not null, "
            + col_nickname + " nvarchar(50), "
            + col_address + " nvarchar(50), "
            + col_gender + " nvarchar(50), "
            + col_about + " nvarchar(50) "
            + ")";


    public UserTable(Context context) {
        super(context);
    }

    public User getUser(String username) {
        Cursor cursor = database.query(nameTable_User, null, col_username + " = ?",
                new String[]{username}, null, null, null);
        if (cursor.moveToFirst()) {
            User user = new User();
            user.setUsername(cursor.getString(1));
            return user;
        }
        return null;
    }

    @Override
    public long insert(Object _obj) {
        User user = (User) _obj;
        if (getUser(user.getUsername()) == null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(col_username, user.getUsername());
            contentValues.put(col_password, user.getPassword());
            contentValues.put(col_about, "");
            contentValues.put(col_address, "");
            contentValues.put(col_email, user.getEmail());
            contentValues.put(col_gender, "");
            contentValues.put(col_nickname, "");
            return database.insert(nameTable_User, null, contentValues);
        }
        return -1;
    }

    @Override
    public long update(Object _obj) {
        return 0;
    }

    @Override
    public long delete(int id) {
        return 0;
    }
}
