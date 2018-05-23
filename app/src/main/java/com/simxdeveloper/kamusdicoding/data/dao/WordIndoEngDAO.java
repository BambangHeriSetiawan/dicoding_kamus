package com.simxdeveloper.kamusdicoding.data.dao;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import com.simxdeveloper.kamusdicoding.data.helper.Const;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;

/**
 * User: simx Date: 23/05/18 23:46
 */
@Dao
public interface WordIndoEngDAO {

  @Query ("SELECT * FROM  "+Const.TABLE_WORD_INDO_ENG)
  Flowable<List<WordsIndoEng>> getAll ();

  @Query ("SELECT * FROM "+ Const.TABLE_WORD_INDO_ENG+ " WHERE word LIKE :query")
  Flowable<List<WordsIndoEng>> getAllBy (String query);

  @Query ("SELECT * FROM word_indo_eng WHERE word LIKE :query")
  Single<WordsIndoEng> getAllByQuery (String query);


  @Insert(onConflict = REPLACE)
  List<Long> insertAll(List<WordsIndoEng> wordsIndoEngs);


  @Insert
  void inserData (WordsIndoEng... wordsIndoEngs);

  @Delete
  void deleteData (WordsIndoEng... wordsIndoEngs);

}
