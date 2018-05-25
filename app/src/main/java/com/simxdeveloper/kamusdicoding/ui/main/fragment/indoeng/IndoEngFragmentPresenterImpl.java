package com.simxdeveloper.kamusdicoding.ui.main.fragment.indoeng;

import android.content.Context;
import com.simxdeveloper.kamusdicoding.Apps;
import com.simxdeveloper.kamusdicoding.data.database.AppDatabases;
import com.simxdeveloper.kamusdicoding.data.presistence.LocalIndoEngDataSource;

/**
 * User: simx Date: 23/05/18 18:36
 */
public class IndoEngFragmentPresenterImpl {
  private Context context;
  private IndoEngFragmentPresenter presenter;
  private LocalIndoEngDataSource localIndoEngDataSource;

  public IndoEngFragmentPresenterImpl (Context context,
      IndoEngFragmentPresenter presenter) {
    this.context = context;
    this.presenter = presenter;
    this.localIndoEngDataSource = new LocalIndoEngDataSource (AppDatabases.getINSTANCE (context).wordIndoEngDAO ());
  }

  public void getDataWordIndoEngl () {
    presenter.showLoading(true);
    localIndoEngDataSource.getAll ().subscribe (
        wordsIndoEngs -> {
          presenter.showLoading (false);
          presenter.initDataWord(wordsIndoEngs);
        },
        throwable -> {
          presenter.showLoading (false);
          presenter.showError(throwable.getMessage ());
        }
    );
  }

  public void queryWord (String query) {
    presenter.showLoading (false);
    localIndoEngDataSource.getAllBy (query).subscribe (
        wordsIndoEngs -> {
          presenter.showLoading (false);
          presenter.initDataWord (wordsIndoEngs);
        },throwable -> {
          presenter.showLoading (false);
          presenter.showError (throwable.getMessage ());
        }
    );
  }
}
