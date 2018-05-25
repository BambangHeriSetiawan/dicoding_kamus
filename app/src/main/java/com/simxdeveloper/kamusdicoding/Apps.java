package com.simxdeveloper.kamusdicoding;

import android.app.Application;
import android.content.Context;
import com.simxdeveloper.kamusdicoding.data.database.AppDatabases;
import com.simxdeveloper.kamusdicoding.data.preload.PreloadDataHelper;
import com.simxdeveloper.kamusdicoding.preference.GlobalPreference;
import com.simxdeveloper.kamusdicoding.preference.PrefKey;

/**
 * User: simx Date: 23/05/18 18:14
 */
public class Apps extends Application {
  private static Context context;

  @Override
  public void onCreate () {
    super.onCreate ();
    context = getApplicationContext ();
    AppDatabases.getINSTANCE (context).wordEngIndoDAO ().insertAll (PreloadDataHelper.loadEngIndWord ());
    AppDatabases.getINSTANCE (context).wordIndoEngDAO ().insertAll (PreloadDataHelper.loadIndEngWord ());
  }

  public static Context getContext () {
    return context;
  }
}
