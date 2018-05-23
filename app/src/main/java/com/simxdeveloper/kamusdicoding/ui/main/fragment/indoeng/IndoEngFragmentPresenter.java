package com.simxdeveloper.kamusdicoding.ui.main.fragment.indoeng;

import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import java.util.List; /**
 * User: simx Date: 23/05/18 18:36
 */
public interface IndoEngFragmentPresenter {

  void showDetail (WordsIndoEng wordsIndoEng);

  void initDataWord (List<WordsIndoEng> wordsIndoEngs);

  void showError (String message);
}
