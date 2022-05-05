package com.example.thuchanh2.data.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.thuchanh2.data.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "th2.db";
    private static final int DB_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE items(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "detail TEXT," +
                "status TEXT," +
                "date TEXT," +
                "isCoop INTEGER" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        Cursor cursor = statement.query("items", null, null, null, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String category = cursor.getString(2);
            String price = cursor.getString(3);
            String date = cursor.getString(4);
            boolean isCoop = cursor.getInt(5) != 0;

            list.add(new Item(id, title, category, price, date, isCoop));
        }

        return list;
    }

    public void addItem(Item item) {
        String sql = "INSERT INTO items(name, detail, status, date, isCoop) values (?,?,?,?,?)";
        String[] args = {item.getName(), item.getDetail(), item.getStatus(), item.getDate(), item.isCoop() ? String.valueOf(1) : String.valueOf(0)};
        SQLiteDatabase statement = getWritableDatabase();
        statement.execSQL(sql, args);
    }

    public void updateItem(Item item) {
        String sql = "UPDATE items " +
                "SET name = ?, detail = ?, status = ?, date = ? , isCoop = ?" +
                "WHERE id = ?";
        String[] args = {item.getName(), item.getDetail(), item.getStatus(), item.getDate(),
                item.isCoop() ? String.valueOf(1) : String.valueOf(0), String.valueOf(item.getId())};
        SQLiteDatabase statement = getWritableDatabase();
        statement.execSQL(sql, args);
    }

    public void deleteItem(int id) {
        String sql = "DELETE FROM items " +
                "WHERE id = ?";
        String[] args = {String.valueOf(id)};
        SQLiteDatabase statement = getWritableDatabase();
        statement.execSQL(sql, args);
    }
}
