package com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.iuh.minhthanghuunghia.lotusflowernt.Model.News;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ThinkPad on 11/11/2017.
 */

public class NewsTable extends MyTable {
    public static final String nameTable_News = "News";
    private static final String col_id = "id";
    public static final String col_idPoster = "accountName";
    public static final String col_time = "time";
    public static final String col_nickname = "username";
    public static final String col_content = "content";
    public static final String col_like = "like";

    public static final String CREATE_NEWS = "CREATE TABLE " +
            nameTable_News + " (" +
            col_id + " integer primary key autoincrement, " +
            col_nickname + " nvarchar(50) not null, " +
            col_idPoster + " nvarchar(50) not null, " +
            col_time + " nvarchar(50) not null, " +
            col_content + " nvarchar(250) not null, " +
            col_like + " nvarchar(6)" +
            ")";

    public NewsTable(Context context) {
        super(context);
    }

    @Override
    public long insert(Object _obj) {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        News news = (News) _obj;
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_nickname, news.getNickName());
        contentValues.put(col_idPoster, news.getUserName());
        contentValues.put(col_time, ft.format(dNow));
        contentValues.put(col_content, news.getContent());
        contentValues.put(col_like, news.isLike() + "");
        return database.insert(nameTable_News, null, contentValues);
    }

    /**
     * Lấy danh sách bảng tin tài khoản đăng
     *
     * @param username thông tin tài khoản
     * @return danh sách bảng tin
     */
    public ArrayList<News> getMyList(String username) {
        ArrayList<News> newsArrayList = new ArrayList<News>();
        Cursor cursor = database.query(nameTable_News, null, col_idPoster + " =?", new String[]{username},
                null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                News news1 = new News();
                news1.setId(cursor.getInt(0) + "");
                news1.setNickName(cursor.getString(1));
                news1.setUserName(cursor.getString(2));
                news1.setTime(cursor.getString(3));
                news1.setContent(cursor.getString(4));
                news1.setLike(cursor.getString(5) == "true" ? true : false);
                newsArrayList.add(news1);
                cursor.moveToNext();
            }
        }
        return newsArrayList;
    }

    /**
     * Lấy danh sách tất cả bảng tin từ CSDL
     *
     * @return danh sách News
     */
    public ArrayList<News> getList() {
        ArrayList<News> newsArrayList = new ArrayList<News>();
        Cursor cursor = database.query(nameTable_News, null, null, null,
                null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                News news1 = new News();
                news1.setId(cursor.getInt(0) + "");
                news1.setNickName(cursor.getString(1));
                news1.setUserName(cursor.getString(2));
                news1.setTime(cursor.getString(3));
                news1.setContent(cursor.getString(4));
                news1.setLike(cursor.getString(5) == "true" ? true : false);
                newsArrayList.add(news1);
                cursor.moveToNext();
            }
        }
        Log.e("tmt updated ttt", newsArrayList.toString());
        return newsArrayList;
    }

    /**
     * Cập nhật News
     *
     * @param _obj News news = new News
     * @return number
     */
    @Override
    public long update(Object _obj) {
        News news = (News) _obj;
        Log.e("tmt uped", news.toString());
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_like, news.isLike() + "");
        contentValues.put(col_content, news.getContent());
        contentValues.put(col_time, news.getTime());
        contentValues.put(col_idPoster, news.getUserName());
        contentValues.put(col_nickname, news.getNickName());
        return database.update(nameTable_News, contentValues,
                col_id + " =?", new String[]{news.getId()});
    }


    @Override
    public long delete(int id) {
        return 0;
    }
}
