package com.simxdeveloper.kamusdicoding.data.presistence;

import android.annotation.SuppressLint;
import com.simxdeveloper.kamusdicoding.data.dao.WordEngIndoDAO;
import com.simxdeveloper.kamusdicoding.data.datasource.WordEngIndoDataSource;
import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * User: simx Date: 24/05/18 0:13
 */
public class LocalEngIndoDataSource implements WordEngIndoDataSource {

  private WordEngIndoDAO wordEngIndoDAO;

  public LocalEngIndoDataSource (
      WordEngIndoDAO wordEngIndoDAO) {
    this.wordEngIndoDAO = wordEngIndoDAO;
  }

  @Override
  public Flowable<List<WordsEngIndo>> getAll () {
    return wordEngIndoDAO.getAll ().subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }

  @Override
  public Flowable<List<WordsEngIndo>> getAllBy (String q) {
    return wordEngIndoDAO.getAllBy (q).subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }

  @SuppressLint("CheckResult")
  @Override
  public Completable insert (final WordsEngIndo wordsEngIndo) {
    return Completable.fromAction (() -> wordEngIndoDAO.inserData (wordsEngIndo)).subscribeOn (Schedulers.newThread ()).observeOn (AndroidSchedulers.mainThread ());
  }

  @Override
  public void deleteAll () {
    wordEngIndoDAO.deleteData ();
  }
}
