package com.simxdeveloper.kamusdicoding;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.RoomDatabase.Callback;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import com.simxdeveloper.kamusdicoding.data.database.AppDatabases;
import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import com.simxdeveloper.kamusdicoding.data.helper.Const;
import com.simxdeveloper.kamusdicoding.data.preload.PreloadDataHelper;
import java.util.ArrayList;

/**
 * User: simx Date: 23/05/18 18:14
 */
public class Apps extends Application {
  private static Context context;
  private static AppDatabases databasesBuilder;
  @Override
  public void onCreate () {
    super.onCreate ();
    context = getApplicationContext ();
  }



  public static Context getContext () {
    return context;
  }
  public  static AppDatabases geAppDatabase(){
    return databasesBuilder;
  }
}
