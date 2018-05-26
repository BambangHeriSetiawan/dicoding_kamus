package com.simxdeveloper.kamusdicoding.data.database;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.simxdeveloper.kamusdicoding.Apps;
import com.simxdeveloper.kamusdicoding.data.dao.WordEngIndoDAO;
import com.simxdeveloper.kamusdicoding.data.dao.WordIndoEngDAO;
import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import com.simxdeveloper.kamusdicoding.data.helper.Const;
import com.simxdeveloper.kamusdicoding.data.preload.PreloadDataHelper;
import com.simxdeveloper.kamusdicoding.preference.GlobalPreference;
import com.simxdeveloper.kamusdicoding.preference.PrefKey;
import java.util.ArrayList;

/**
 * User: simx Date: 23/05/18 23:57
 */
@Database (entities = {WordsIndoEng.class, WordsEngIndo.class},version = Const.DATABASE_VERSION,exportSchema = false)
public abstract class AppDatabases extends RoomDatabase {
  private static AppDatabases INSTANCE;
  private static Boolean isTransaction;
  public abstract WordEngIndoDAO wordEngIndoDAO();
  public abstract WordIndoEngDAO wordIndoEngDAO();
  public static AppDatabases getINSTANCE(Context context){
    if (INSTANCE == null) {
      synchronized (AppDatabases.class){
        INSTANCE = Room.databaseBuilder (context.getApplicationContext (),AppDatabases.class,Const.DATABASE_NAME)
            .allowMainThreadQueries ()
            .addCallback (callback)
            .build ();
      }
    }
    return INSTANCE;
  }
  public static Builder<AppDatabases> getBuilder(Context context){
    return Room.databaseBuilder (context.getApplicationContext (),AppDatabases.class,Const.DATABASE_NAME)
        .allowMainThreadQueries ();
  }

  public void destroyInstance(){
    INSTANCE = null;
  }

  public static RoomDatabase.Callback callback = new Callback () {
    @Override
    public void onCreate (@NonNull SupportSQLiteDatabase db) {
      super.onCreate (db);
      db.enableWriteAheadLogging ();
      ArrayList<WordsEngIndo> wordsEngIndos = PreloadDataHelper.loadEngIndWord ();
      ArrayList<WordsIndoEng> wordsIndoEngs = PreloadDataHelper.loadIndEngWord ();
      insertEng(wordsEngIndos,db);
      insertIndo(wordsIndoEngs,db);
      GlobalPreference.write (PrefKey.isFirsLoad,true,Boolean.class);
    }

    @Override
    public void onOpen (@NonNull SupportSQLiteDatabase db) {
      super.onOpen (db);
      db.enableWriteAheadLogging ();
      isTransaction = db.isDatabaseIntegrityOk ();
      Log.e ("AppDatabases", "onOpen: isDatabaseIntegrityOk =  " + db.isDatabaseIntegrityOk ());
    }
  };
  public static Builder<AppDatabases> build(Context context){
    return Room.databaseBuilder (context.getApplicationContext (),AppDatabases.class, Const.DATABASE_NAME)
            .allowMainThreadQueries ();
  }
  private static void insertIndo (ArrayList<WordsIndoEng> wordsIndoEngs, SupportSQLiteDatabase db) {
    for (WordsIndoEng wordsIndoEng : wordsIndoEngs){
      ContentValues initialValues =  new ContentValues();
      initialValues.put(Const.ROW_WORD, wordsIndoEng.getWord ());
      initialValues.put(Const.ROW_DESC, wordsIndoEng.getDesc ());
      db.insert (Const.TABLE_WORD_INDO_ENG,REPLACE,initialValues);
    }
  }

  private static void insertEng (ArrayList<WordsEngIndo> wordsEngIndos, SupportSQLiteDatabase db) {
    for (WordsEngIndo wordsEngIndo : wordsEngIndos){
      ContentValues initialValues =  new ContentValues();
      initialValues.put(Const.ROW_WORD, wordsEngIndo.getWord ());
      initialValues.put(Const.ROW_DESC, wordsEngIndo.getDesc ());
      db.insert (Const.TABLE_WORD_ENG_INDO,REPLACE,initialValues);
    }
  }
  public static Boolean getTransactions (){
    return getINSTANCE (Apps.getContext ()).inTransaction ();
  }

  @NonNull
  @Override
  protected InvalidationTracker createInvalidationTracker () {
    Log.e ("AppDatabases", "createInvalidationTracker: " );
    return null;
  }
}
