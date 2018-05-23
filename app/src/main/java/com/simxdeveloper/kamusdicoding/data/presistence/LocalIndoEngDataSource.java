package com.simxdeveloper.kamusdicoding.data.presistence;

import com.simxdeveloper.kamusdicoding.data.dao.WordIndoEngDAO;
import com.simxdeveloper.kamusdicoding.data.datasource.WordIndoEngDataSource;
import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * User: simx Date: 24/05/18 0:13
 */
public class LocalIndoEngDataSource implements WordIndoEngDataSource {

  private WordIndoEngDAO wordIndoEngDAO;

  public LocalIndoEngDataSource (
      WordIndoEngDAO wordIndoEngDAO) {
    this.wordIndoEngDAO = wordIndoEngDAO;
  }

  @Override
  public Flowable<List<WordsIndoEng>> getAll () {
    return wordIndoEngDAO.getAll ().subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }


  @Override
  public Flowable<List<WordsIndoEng>> getAllBy (String q) {
    return wordIndoEngDAO.getAllBy (q).subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }

  @Override
  public Completable insert (WordsIndoEng wordsIndoEng) {
    return Completable.fromAction (() -> wordIndoEngDAO.inserData (wordsIndoEng)).subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }

  @Override
  public void deleteAll () {
    wordIndoEngDAO.deleteData ();
  }
}
