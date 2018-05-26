package com.simxdeveloper.kamusdicoding.ui.splahs;

import android.content.Context;
import android.util.Log;
import com.simxdeveloper.kamusdicoding.Apps;
import com.simxdeveloper.kamusdicoding.R;
import com.simxdeveloper.kamusdicoding.data.database.AppDatabases;
import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import com.simxdeveloper.kamusdicoding.data.presistence.LocalEngIndoDataSource;
import com.simxdeveloper.kamusdicoding.data.presistence.LocalIndoEngDataSource;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;

/**
 * User: simx Date: 23/05/18 18:38
 */
public class SplashPresenterImpl {
  private Context context;
  private SplashPresenter presenter;
  public SplashPresenterImpl (SplashPresenter presenter, Context context) {
    this.presenter = presenter;
    this.context = context;
  }


  public void checkTransactionDatabase (boolean isTransacation) {
    if (isTransacation){
      presenter.showProgress (true,Apps.getContext ().getResources ().getString (R.string.loading));
    }else {
      presenter.gotoMain ();
    }
  }
}
