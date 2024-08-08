package com.example.quanlysach;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "MyDatabaseHelper";
    private static final String DATABASE_NAME = "DICTIONARY_DB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_BOOK = "BOOK";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "NAME";
    private static final String CREATE_BOOK_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_BOOK + " (" +
                    ID_COLUMN + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    NAME_COLUMN + " TEXT NOT NULL" + ")";
    private static MyDatabaseHelper sInstance;

    public static MyDatabaseHelper getInstance(Context context) {
        if (sInstance == null)
            sInstance = new MyDatabaseHelper(context.getApplicationContext());
        return sInstance;
    }

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_BOOK_TABLE_SQL);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        onCreate(db);
    }

    public boolean insertBook(Book book) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_COLUMN, book.getMaTg());
        values.put(NAME_COLUMN, book.getTenTg());
        long rowID = db.insert(TABLE_BOOK, null, values);
        db.close();
        if (rowID != -1)
            return false;
        return true;
    }

    public Book getBook(String id) {
        SQLiteDatabase db = getReadableDatabase();
        Book book = null;
        Cursor cursor = db.query(TABLE_BOOK, new String[]{ID_COLUMN, NAME_COLUMN},
                ID_COLUMN + "= ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            book = new Book(cursor.getString(0), cursor.getString(1));
            cursor.close();
        }
        db.close();
        return book;
    }

    @SuppressLint("Range")
    public List<Book> getAllBook(){
        SQLiteDatabase db = getReadableDatabase();
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_BOOK;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                books.add(new
                        Book(cursor.getString(cursor.getColumnIndex(ID_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(NAME_COLUMN))));
            }while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return books;
    }

    public int getTotalBook(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_BOOK;
        Cursor cursor = db.rawQuery(sql, null);
        int total = cursor.getCount();
        cursor.close();
        db.close();
        return total;
    }

    public int updateBook(Book book){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues value = new ContentValues();
        value.put(ID_COLUMN, book.getMaTg());
        value.put(NAME_COLUMN, book.getTenTg());

        int rowEffect = db.update(TABLE_BOOK, value, ID_COLUMN + " = ?",
                new String[]{String.valueOf(book.getMaTg())});
        db.close();
        return rowEffect;  // trả về số lượng hàng đã được cập nhật
    }

    public int deleteAllBook(){
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(TABLE_BOOK, null, null);
        db.close();
        return rowEffect;
    }

    public int deleteBook(Book book) {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(TABLE_BOOK, ID_COLUMN + " = ?", new
                String[]{String.valueOf(book.getMaTg())});
        db.close();
        return rowEffect;
    }
}





