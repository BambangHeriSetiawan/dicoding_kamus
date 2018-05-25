package com.simxdeveloper.kamusdicoding.data.preload;

import android.content.res.Resources;
import android.util.Log;
import com.simxdeveloper.kamusdicoding.Apps;
import com.simxdeveloper.kamusdicoding.R;
import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * User: simx Date: 24/05/18 0:36
 */
public class PreloadDataHelper {
  public static ArrayList<WordsEngIndo> loadEngIndWord(){
    ArrayList<WordsEngIndo> wordsEngIndoArrayList = new ArrayList<> ();
    String line = null;
    BufferedReader reader;
    try {
      Resources res = Apps.getContext ().getResources ();
      InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);

      reader = new BufferedReader(new InputStreamReader(raw_dict));
      int count = 0;
      do {
        line = reader.readLine();
        String[] splitstr = line.split("\t");
        WordsEngIndo wordsEngIndo;

        wordsEngIndo = new WordsEngIndo (splitstr[0], splitstr[1]);
        wordsEngIndoArrayList.add(wordsEngIndo);
        count++;
      } while (line != null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return wordsEngIndoArrayList;
  }
  public static ArrayList<WordsIndoEng> loadIndEngWord(){
    ArrayList<WordsIndoEng> wordsIndoEngArrayList = new ArrayList<> ();
    String line = null;
    BufferedReader reader;
    try {
      Resources res = Apps.getContext ().getResources ();
      InputStream raw_dict = res.openRawResource(R.raw.indonesia_english);

      reader = new BufferedReader(new InputStreamReader(raw_dict));
      int count = 0;
      do {
        line = reader.readLine();
        String[] splitstr = line.split("\t");
        WordsIndoEng wordsIndoEng = new WordsIndoEng (splitstr[0], splitstr[1]);
        wordsIndoEngArrayList.add(wordsIndoEng);
        count++;
      } while (line != null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return wordsIndoEngArrayList;
  }
}
