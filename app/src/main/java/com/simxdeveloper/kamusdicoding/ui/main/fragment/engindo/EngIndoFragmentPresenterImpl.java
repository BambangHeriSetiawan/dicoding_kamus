package com.simxdeveloper.kamusdicoding.ui.main.fragment.engindo;

import android.content.Context;
import android.util.Log;
import com.simxdeveloper.kamusdicoding.data.database.AppDatabases;
import com.simxdeveloper.kamusdicoding.data.presistence.LocalEngIndoDataSource;

/**
 * User: simx Date: 23/05/18 18:36
 */
public class EngIndoFragmentPresenterImpl {
  private EngIndoFragmentPresenter presenter;
  private Context context;
  private LocalEngIndoDataSource localEngIndoDataSource;
  public EngIndoFragmentPresenterImpl (
      EngIndoFragmentPresenter presenter, Context context) {
    this.presenter = presenter;
    this.context = context;
    this.localEngIndoDataSource = new LocalEngIndoDataSource (AppDatabases.getINSTANCE (context).wordEngIndoDAO ());
  }

  public void loadDataWord () {
   localEngIndoDataSource.getAll ().subscribe (
       wordsEngIndos -> {
         Log.e ("enee", "loadDataWord: " + wordsEngIndos.toString ());
         presenter.initDataWord (wordsEngIndos);
       },throwable -> {
         presenter.showErorr (throwable.getMessage ());
       },() -> {
         Log.e ("EngIndoFragmen", "loadDataWord: ");
       }
   );
  }

  public void queryWord (String query) {
    localEngIndoDataSource.getAllBy (query).subscribe (
        wordsEngIndos -> {
          presenter.initDataWord (wordsEngIndos);
        },
        throwable -> presenter.showErorr (throwable.getMessage ())
    );
  }
}
