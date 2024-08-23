package com.example.s1723;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String dbname = "student";
    static final String tablename = "studentdetail";
    static final String prncol = "studentPRN";
    static final String namecol = "studentNAME";
    SQLiteDatabase sqldb;

    public DatabaseHandler(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE studentdetail(studentPRN,studentNAME)";
        db.execSQL(query);
    }

    public void opendb()
    {
        sqldb = getWritableDatabase();
    }

    public void insertvalue(String prn, String name)
    {
        ContentValues cv = new ContentValues();
        cv.put(prncol,prn);
        cv.put(namecol,name);
        sqldb.insert(tablename,null,cv);
    }

    public void closedb()
    {
        sqldb.close();
    }

public Cursor selectdata()
{
    SQLiteDatabase db = this.getReadableDatabase();
    return sqldb.rawQuery("select * from studentdetail", null);
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
