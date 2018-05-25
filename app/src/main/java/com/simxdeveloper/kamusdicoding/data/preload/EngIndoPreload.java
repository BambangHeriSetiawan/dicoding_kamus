package com.simxdeveloper.kamusdicoding.data.preload;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.simxdeveloper.kamusdicoding.data.datasource.DatabaseHelper;
import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import com.simxdeveloper.kamusdicoding.data.helper.Const;

/**
 * User: simx Date: 25/05/18 14:01
 */
public class EngIndoPreload {
  private Context context;
  private DatabaseHelper databaseHelper;
  private SQLiteDatabase database;

  public EngIndoPreload (Context context) {
    this.context = context;
  }
  public EngIndoPreload open() throws SQLException {
    databaseHelper = new DatabaseHelper (context);
    database = databaseHelper.getWritableDatabase ();
    return this;
  }
  public  void close(){
    databaseHelper.close ();
  }
  public void beginTransaction(){
    database.beginTransaction();
  }
  public long insert(WordsEngIndo wordsEngIndo){
    ContentValues initialValues =  new ContentValues();
    initialValues.put(Const.ROW_WORD, wordsEngIndo.getWord ());
    initialValues.put(Const.ROW_DESC, wordsEngIndo.getDesc ());
    return database.insert(Const.TABLE_WORD_ENG_INDO, null, initialValues);
  }
  public void setTransactionSuccess(){
    database.setTransactionSuccessful();
  }

  public void endTransaction(){
    database.endTransaction();
  }
  public void insertTransaction(WordsIndoEng wordsIndoEng){
    String sql = String.format ("INSERT INTO "+Const.TABLE_WORD_ENG_INDO+" ("+Const.ROW_WORD+", "+Const.ROW_DESC
        +") VALUES (?, ?)");
    SQLiteStatement stmt = database.compileStatement(sql);
    stmt.bindString(1, wordsIndoEng.getWord ());
    stmt.bindString(2, wordsIndoEng.getDesc ());
    stmt.execute();
    stmt.clearBindings();

  }
}
