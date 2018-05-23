package com.simxdeveloper.kamusdicoding.data.datasource;

import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.List;

/**
 * User: simx Date: 24/05/18 0:14
 */
public interface WordIndoEngDataSource {

  Flowable<List<WordsIndoEng>> getAll ();

  Flowable<List<WordsIndoEng>> getAllBy (String q);

  Completable insert (WordsIndoEng wordsIndoEng);

  void deleteAll ();
}
