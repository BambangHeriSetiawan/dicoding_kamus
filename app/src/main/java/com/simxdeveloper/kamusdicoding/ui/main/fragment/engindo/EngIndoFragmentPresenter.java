package com.simxdeveloper.kamusdicoding.ui.main.fragment.engindo;

import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import java.util.List; /**
 * User: simx Date: 23/05/18 18:36
 */
public interface EngIndoFragmentPresenter {

  void showDetail (WordsEngIndo wordsEngIndo);

  void initDataWord (List<WordsEngIndo> wordsEngIndos);

  void showErorr (String message);

  void showLoading (boolean show);
}
