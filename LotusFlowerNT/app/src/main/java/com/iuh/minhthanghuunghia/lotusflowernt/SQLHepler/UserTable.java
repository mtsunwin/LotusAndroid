package com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

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
    private static final String col_id = "id";
    private static final int id = 0;
    private static final int username = 1;
    private static final int password = 2;
    private static final int email = 3;
    private static final int nickname = 4;
    private static final int address = 5;
    private static final int gender = 6;
    private static final int about = 7;


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

    public User login(String _username, String _password, String _email) {
        String str = col_username + " = ? and " + col_password + " = ?";
        String[] arStrings = new String[]{_username, _password};
        if (_email != null) {
            str += " and " + col_email + " =?";
            arStrings = new String[]{_username, _password, _email};
        }
        Cursor cursor = database.query(nameTable_User, null, str, arStrings,
                null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            return new User(cursor.getString(username), cursor.getString(email), cursor.getString(nickname),
                    cursor.getString(address), cursor.getString(gender), cursor.getString(about));
        }
        return null;
    }

    @Override
    public long insert(Object _obj) {
        User user = (User) _obj;
        if (login(user.getUsername(), user.getPassword(), user.getEmail()) == null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(col_username, user.getUsername());
            contentValues.put(col_password, user.getPassword());
            contentValues.put(col_about, "");
            contentValues.put(col_address, "");
            contentValues.put(col_email, user.getEmail());
            contentValues.put(col_gender, "");
            contentValues.put(col_nickname, "");
            Log.e("tmt", "database inserted");
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

    public User getInfor(String username) {
        Cursor cursor = database.query(nameTable_User, null, col_username + " = ?",
                new String[]{username},
                null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            User user = new User();
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            user.setNickname(cursor.getString(4));
            user.setAddress(cursor.getString(5));
            user.setGender(cursor.getString(6));
            user.setAbout(cursor.getString(7));
            return user;
        }
        return null;
    }

    /***
     * Thực hiện xóa dữ liệu trong table User
     * @return (>0) true, (<0) false
     */
    public long clearList() {
        return database.delete(nameTable_User, null, null);
    }
}
