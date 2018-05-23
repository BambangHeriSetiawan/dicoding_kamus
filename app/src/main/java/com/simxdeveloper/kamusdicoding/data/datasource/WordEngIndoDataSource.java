package com.simxdeveloper.kamusdicoding.data.datasource;

import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.List;

/**
 * User: simx Date: 24/05/18 0:14
 */
public interface WordEngIndoDataSource {

  Flowable<List<WordsEngIndo>> getAll();

  Flowable<List<WordsEngIndo>> getAllBy(String q);

  Completable insert(WordsEngIndo wordsEngIndo);

  void deleteAll();
}
