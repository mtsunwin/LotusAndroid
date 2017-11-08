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

    public boolean login(User user) {
        String str = col_username + " = ? and " + col_password + " = ?";
        String[] arStrings = new String[]{user.getUsername(), user.getPassword()};
        if (user.getEmail() instanceof String) {
            str += " and " + col_email + " =?";
            arStrings = new String[]{user.getUsername(), user.getPassword(), user.getEmail()};
        }
        Cursor cursor = database.query(nameTable_User, null, str, arStrings,
                null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0)
            return true;
        return false;
    }

    @Override
    public long insert(Object _obj) {
        User user = (User) _obj;
        if (!login(user)) {
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
