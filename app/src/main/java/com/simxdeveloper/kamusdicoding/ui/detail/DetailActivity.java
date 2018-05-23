package com.simxdeveloper.kamusdicoding.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.simxdeveloper.kamusdicoding.R;

public class DetailActivity extends AppCompatActivity {

  @BindView(R.id.tv_word)
  TextView tvWord;
  @BindView(R.id.tv_word_desc)
  TextView tvWordDesc;
  @BindView(R.id.toolbar)
  Toolbar toolbar;

  public static void start (Context context, String word, String desc) {
    Intent starter = new Intent (context, DetailActivity.class);
    starter.putExtra ("word", word);
    starter.putExtra ("desc", desc);
    context.startActivity (starter);
  }

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_detail);
    ButterKnife.bind (this);
    setSupportActionBar (toolbar);
    getSupportActionBar ().setDisplayShowTitleEnabled (true);
    getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
    getSupportActionBar ().setHomeAsUpIndicator (getResources ().getDrawable (R.drawable.ic_arrow_back_white_24dp));
    if (getIntent ().getExtras () != null) {
      String word = getIntent ().getStringExtra ("word");
      String desc = getIntent ().getStringExtra ("desc");
      tvWord.setText (word);
      tvWordDesc.setText (desc);
    }
  }

  @Override
  public boolean onOptionsItemSelected (MenuItem item) {
    switch (item.getItemId ()) {
      case android.R.id.home:
        onBackPressed ();
        break;
    }
    return super.onOptionsItemSelected (item);
  }
}
