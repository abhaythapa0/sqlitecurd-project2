package com.example.sqlite_project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="username.db";
    public static final String TABLE_NAME="username_table";
    public static final String COL1 = "name";
    public static final String COL2 = "description";
    public static final String COL3= "image";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (" + COL1 + " TEXT, " + COL2 + " TEXT, " + COL3 + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String description, String imageUri){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1, name);
        cv.put(COL2, description);
        cv.put(COL3, imageUri);
    long result = sqLiteDatabase.insert(TABLE_NAME, null, cv);
    return result != -1;
    }

    public Cursor getAllItems(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
    }
}

