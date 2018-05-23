package com.simxdeveloper.kamusdicoding.ui.splahs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.simxdeveloper.kamusdicoding.R;
import com.simxdeveloper.kamusdicoding.data.helper.PreloadDataHelper;
import com.simxdeveloper.kamusdicoding.preference.GlobalPreference;
import com.simxdeveloper.kamusdicoding.preference.PrefKey;
import com.simxdeveloper.kamusdicoding.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity implements SplashPresenter {

  @BindView(R.id.progress_view)
  ProgressBar progressView;
  @BindView(R.id.tv_progress)
  TextView tvProgress;
  private SplashPresenterImpl presenter;

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_splash);
    ButterKnife.bind (this);
    presenter = new SplashPresenterImpl (this, this);
    if (!GlobalPreference.read (PrefKey.isFirsLoad, Boolean.class)) {
      presenter.loadDataEngIndoWord (PreloadDataHelper.loadEngIndWord ());
    } else {
      gotoMain ();
    }
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
    Log.e ("SplashActivity", "insertIndoEng: " );
    presenter.loadDataIndoEngWord (PreloadDataHelper.loadIndEngWord ());
  }

  @Override
  public void gotoMain () {
    GlobalPreference.write (PrefKey.isFirsLoad, true, Boolean.class);
    MainActivity.start (this);
    this.finish ();
  }
}
