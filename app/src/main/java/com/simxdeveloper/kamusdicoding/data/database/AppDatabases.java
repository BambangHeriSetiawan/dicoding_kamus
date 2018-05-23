package com.simxdeveloper.kamusdicoding.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.simxdeveloper.kamusdicoding.data.dao.WordEngIndoDAO;
import com.simxdeveloper.kamusdicoding.data.dao.WordIndoEngDAO;
import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import com.simxdeveloper.kamusdicoding.data.helper.Const;

/**
 * User: simx Date: 23/05/18 23:57
 */
@Database (entities = {WordsIndoEng.class, WordsEngIndo.class},version = Const.DATABASE_VERSION,exportSchema = false)
public abstract class AppDatabases extends RoomDatabase {
  private static AppDatabases INSTANCE;

  public abstract WordEngIndoDAO wordEngIndoDAO();
  public abstract WordIndoEngDAO wordIndoEngDAO();
  public static AppDatabases getINSTANCE(Context context){
    if (INSTANCE == null) {
      synchronized (AppDatabases.class){
        INSTANCE = Room.databaseBuilder (context.getApplicationContext (),AppDatabases.class,Const.DATABASE_NAME)
            .allowMainThreadQueries ()
            .build ();
      }
    }
    return INSTANCE;
  }
  public void destroyInstance(){
    INSTANCE = null;
  }

}
