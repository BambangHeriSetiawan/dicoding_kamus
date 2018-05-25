package com.simxdeveloper.kamusdicoding.data.preload;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.simxdeveloper.kamusdicoding.data.datasource.DatabaseHelper;
import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import com.simxdeveloper.kamusdicoding.data.helper.Const;

/**
 * User: simx Date: 25/05/18 14:01
 */
public class IndoEngPreload {
  private Context context;
  private DatabaseHelper databaseHelper;
  private SQLiteDatabase database;

  public IndoEngPreload (Context context) {
    this.context = context;
  }
  public IndoEngPreload open() throws SQLException {
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
  public long insert(WordsIndoEng wordsIndoEng){
    ContentValues initialValues =  new ContentValues();
    initialValues.put(Const.ROW_WORD, wordsIndoEng.getWord ());
    initialValues.put(Const.ROW_DESC, wordsIndoEng.getDesc ());
    return database.insert(Const.TABLE_WORD_ENG_INDO, null, initialValues);
  }
}
