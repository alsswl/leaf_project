package com.example.leaf;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoteDatabase {

    private static NoteDatabase database;
    public static String Table_note = "NOTE";
    public static int DataBase_Version = 1;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    private NoteDatabase(Context context) {
        this.context = context;
    }

    public static NoteDatabase getInstance(Context context) {
        if (database == null) {
            database = new NoteDatabase(context);
        }
        return database;
    }

    public boolean open() {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return true;

    }

    public void close() {
        db.close();

        database = null;
    }
    //조회
    public Cursor rawQuery(String SQL) {

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(SQL, null);

        } catch (Exception ex) {

        }
        return  cursor;
    }

    public boolean execSQL(String SQL){
        try{
            db.execSQL(SQL);
        }catch(Exception e) {
            return false;
        }
        return true;
    }
     //데이터베이스 헬퍼
    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(@Nullable Context context) {
            super(context, AppConstants.DATABASE_NAME, null, DataBase_Version);
        }



        @Override
        public void onCreate(SQLiteDatabase db) {
            String DROP_SQL = "drop table if exists "+ Table_note;
            try{
                db.execSQL(DROP_SQL);
            }catch (Exception e){}

            String CREATE_SQL = "create table " + Table_note + "("
                    +" _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                    +" THEME TEXT DEFAULT '',"
                    +" CONTENTS TEXT DEFAULT '',"
                    +" MOOD TEXT,"
                    +" YEAR INTEGER,"
                    +" MONTH INTEGER,"
                    +" DAY INTEGER"
                    +")";
            try{
                db.execSQL(CREATE_SQL);
            } catch (Exception e){}



        }



        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

        @Override
        public void onOpen(SQLiteDatabase db) {

        }
    }

}
