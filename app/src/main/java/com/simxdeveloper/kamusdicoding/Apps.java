package com.simxdeveloper.kamusdicoding;

import android.app.Application;
import android.content.Context;

/**
 * User: simx Date: 23/05/18 18:14
 */
public class Apps extends Application {
  private static Context context;

  @Override
  public void onCreate () {
    super.onCreate ();
    context = getApplicationContext ();
  }

  public static Context getContext () {
    return context;
  }
}
