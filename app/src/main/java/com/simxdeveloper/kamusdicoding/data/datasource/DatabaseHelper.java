package com.simxdeveloper.kamusdicoding.data.datasource;

import static android.provider.BaseColumns._ID;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.simxdeveloper.kamusdicoding.data.helper.Const;

/**
 * User: simx Date: 25/05/18 15:46
 */
public class DatabaseHelper extends SQLiteOpenHelper {


  public static String CREATE_TABLE_ENGINDO = String.format ("CREATE TABLE "+Const.TABLE_WORD_ENG_INDO +
      " ("+_ID+" integer primary key autoincrement, " +
      Const.ROW_WORD+" text not null, " +
      Const.ROW_DESC+" text not null);");
  public static String CREATE_TABLE_INDOENG = String.format ("CREATE TABLE "+Const.TABLE_WORD_ENG_INDO +
      " ("+_ID+" integer primary key autoincrement, " +
      Const.ROW_WORD+" text not null, " +
      Const.ROW_DESC+" text not null);");


  public DatabaseHelper (Context context) {
    super (context, Const.DATABASE_NAME, null, Const.DATABASE_VERSION);
  }

  @Override
  public void onCreate (SQLiteDatabase db) {
    db.execSQL (CREATE_TABLE_ENGINDO);
    db.execSQL (CREATE_TABLE_INDOENG);
  }

  @Override
  public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+Const.TABLE_WORD_ENG_INDO);
    db.execSQL("DROP TABLE IF EXISTS "+Const.TABLE_WORD_INDO_ENG);
    onCreate (db);
  }
}
