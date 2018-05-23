package com.simxdeveloper.kamusdicoding.ui.splahs;

import android.content.Context;
import android.util.Log;
import com.simxdeveloper.kamusdicoding.data.database.AppDatabases;
import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import com.simxdeveloper.kamusdicoding.data.helper.PreloadDataHelper;
import com.simxdeveloper.kamusdicoding.data.presistence.LocalEngIndoDataSource;
import com.simxdeveloper.kamusdicoding.data.presistence.LocalIndoEngDataSource;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

/**
 * User: simx Date: 23/05/18 18:38
 */
public class SplashPresenterImpl {
  private Context context;
  private SplashPresenter presenter;
  private LocalEngIndoDataSource localEngIndoDataSource;
  private LocalIndoEngDataSource localIndoEngDataSource;
  public SplashPresenterImpl (SplashPresenter presenter, Context context) {
    this.presenter = presenter;
    this.context = context;
    this.localEngIndoDataSource = new LocalEngIndoDataSource (AppDatabases.getINSTANCE (context).wordEngIndoDAO ());
    this.localIndoEngDataSource = new LocalIndoEngDataSource (AppDatabases.getINSTANCE (context).wordIndoEngDAO ());
  }

  public void loadDataEngIndoWord (ArrayList<WordsEngIndo> wordsEngIndos) {
    presenter.showProgress(true, "Load data eng indo");
    Observable.fromCallable (()->AppDatabases.getINSTANCE (context).wordEngIndoDAO ().insertAll (wordsEngIndos)).subscribeOn (
        Schedulers.newThread ()).observeOn (AndroidSchedulers.mainThread ()).subscribe (longs -> {
    },throwable -> {
      Log.e ("SplashPresenterImpl", "loadDataEngIndoWord: " + throwable.getMessage ());
    },() -> {
          presenter.showProgress (false,"");
          presenter.insertIndoEng ();
    });

  }


  public void loadDataIndoEngWord (ArrayList<WordsIndoEng> wordsIndoEngs) {
    presenter.showProgress (true,"Load data indo eng");
    Observable.fromCallable (()->AppDatabases.getINSTANCE (context).wordIndoEngDAO ().insertAll (wordsIndoEngs)).subscribeOn (
        Schedulers.newThread ()).observeOn (AndroidSchedulers.mainThread ()).subscribe (longs -> {
    },throwable -> {
      Log.e ("SplashPresenterImpl", "loadDataEngIndoWord: " + throwable.getMessage ());
    },() -> {
      presenter.showProgress (false,"");
      presenter.gotoMain ();
    });
  }

}
