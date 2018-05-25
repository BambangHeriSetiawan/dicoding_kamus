package com.simxdeveloper.kamusdicoding.ui.splahs;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase.Callback;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.simxdeveloper.kamusdicoding.R;
import com.simxdeveloper.kamusdicoding.data.database.AppDatabases;
import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import com.simxdeveloper.kamusdicoding.data.helper.Const;
import com.simxdeveloper.kamusdicoding.data.preload.EngIndoPreload;
import com.simxdeveloper.kamusdicoding.data.preload.IndoEngPreload;
import com.simxdeveloper.kamusdicoding.data.preload.PreloadDataHelper;
import com.simxdeveloper.kamusdicoding.preference.GlobalPreference;
import com.simxdeveloper.kamusdicoding.preference.PrefKey;
import com.simxdeveloper.kamusdicoding.ui.main.MainActivity;
import java.util.ArrayList;
import java.util.concurrent.Executors;

public class SplashActivity extends AppCompatActivity implements SplashPresenter {

  @BindView(R.id.progress_view)
  ProgressBar progressView;
  @BindView(R.id.tv_progress)
  TextView tvProgress;
  private SplashPresenterImpl presenter;
  private ArrayList<WordsEngIndo> wordsEngIndos = new ArrayList<> ();
  private ArrayList<WordsIndoEng> wordsIndoEngs = new ArrayList<> ();
  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_splash);
    ButterKnife.bind (this);
    presenter = new SplashPresenterImpl (this, this);
    showProgress (true,"Loading data ....");
    new Handler ().postDelayed (() -> gotoMain (),1000);
  }

  @Override
  public void showProgress (boolean show, String load_data_eng_indo) {
    if (show) {
      progressView.setVisibility (View.VISIBLE);
      tvProgress.setText (load_data_eng_indo);
    } else {
      progressView.setVisibility (View.GONE);
      tvProgress.setVisibility (View.GONE);
    }
  }

  @Override
  public void insertIndoEng () {
    //presenter.loadDataIndoEngWord (PreloadDataHelper.loadIndEngWord ());
  }

  @Override
  public void gotoMain () {
    GlobalPreference.write (PrefKey.isFirsLoad, true, Boolean.class);
    MainActivity.start (this);
    this.finish ();
  }

}
