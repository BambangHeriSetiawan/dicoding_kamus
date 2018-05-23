package com.simxdeveloper.kamusdicoding.data.dao;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import com.simxdeveloper.kamusdicoding.data.helper.Const;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;

/**
 * User: simx Date: 23/05/18 23:46
 */
@Dao
public interface WordEngIndoDAO {

  @Query ("SELECT * FROM word_eng_indo")
  Flowable<List<WordsEngIndo>> getAll();

  @Query ("SELECT * FROM "+ Const.TABLE_WORD_ENG_INDO+ " WHERE " +Const.ROW_WORD + " LIKE :query")
  Flowable<List<WordsEngIndo>> getAllBy(String query);

  @Insert(onConflict = REPLACE)
  List<Long> insertAll (List<WordsEngIndo> wordsEngIndos);

  @Insert
  void inserData(WordsEngIndo...wordsEngIndos);

  @Delete
  void deleteData(WordsEngIndo...wordsEngIndos);

}
