package com.example.android.schoolsystem.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shafy on 14/12/2017.
 */

public class SystemDBHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME="school.db";
    private final static int DATABASE_VERSION=1;

    public SystemDBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQLQuery="CREATE TABLE " + SystemContract.ClassRoomsEntry.TABLE_NAME+" ("+
                SystemContract.ClassRoomsEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                SystemContract.ClassRoomsEntry.COL_ROOM_NAME+" TEXT UNIQUE, "+
                SystemContract.ClassRoomsEntry.COL_ROOM_CAP+" INTEGER );"
                ;
        db.execSQL(SQLQuery);

        String SQLQuery2="CREATE TABLE " + SystemContract.DepartmentsEntry.TABLE_NAME+" ("+
                SystemContract.DepartmentsEntry.DEPARTMENT_NO+" INTEGER PRIMARY KEY, "+
                SystemContract.DepartmentsEntry.DEPARTMENT_NAME+" TEXT );"
                ;
        db.execSQL(SQLQuery2);

        String SQLQuery3="CREATE TABLE " + SystemContract.LevelsEntry.TABLE_NAME+" ("+
                SystemContract.LevelsEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                SystemContract.LevelsEntry.COL_NAME +" TEXT UNIQUE, "+
                SystemContract.LevelsEntry.COL_DES+" TEXT );"
                ;
        db.execSQL(SQLQuery3);

        String SQLQuery4 ="CREATE TABLE " +SystemContract.CoursesEntry.TABLE_NAME +" ("+
                SystemContract.CoursesEntry.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                SystemContract.CoursesEntry.COL_NAME +" TEXT, "+
                SystemContract.CoursesEntry.COL_LVL_ID+" INTEGER , "+
                SystemContract.CoursesEntry.COL_CLASS_ID+" INTEGER );"
                ;
        db.execSQL(SQLQuery4);

        String SQLQuery5 ="CREATE TABLE " +SystemContract.StudentsEntry.TABLE_NAME +" ("+
                SystemContract.StudentsEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                SystemContract.StudentsEntry.COL_NAME +" TEXT, "+
                SystemContract.StudentsEntry.COL_ADDRESS+" TEXT , "+
                SystemContract.StudentsEntry.COL_DOB+" TEXT , "+
                SystemContract.StudentsEntry.COL_EMAIL+" TEXT );"
                ;
        db.execSQL(SQLQuery5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQLQuery="DROP TABLE IF EXISTS " + SystemContract.ClassRoomsEntry.TABLE_NAME ;
        db.execSQL(SQLQuery);
        String SQLQuery2="DROP TABLE IF EXISTS " + SystemContract.DepartmentsEntry.TABLE_NAME ;
        db.execSQL(SQLQuery2);
        String SQLQuery4="DROP TABLE IF EXISTS " + SystemContract.LevelsEntry.TABLE_NAME ;
        db.execSQL(SQLQuery4);
        String SQLQuery3="DROP TABLE IF EXISTS " + SystemContract.CoursesEntry.TABLE_NAME ;
        db.execSQL(SQLQuery3);
        String SQLQuery5="DROP TABLE IF EXISTS " + SystemContract.StudentsEntry.TABLE_NAME ;
        db.execSQL(SQLQuery5);
        onCreate(db);
    }
}
