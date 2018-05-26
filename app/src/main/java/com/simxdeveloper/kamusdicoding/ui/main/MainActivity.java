package com.simxdeveloper.kamusdicoding.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.simxdeveloper.kamusdicoding.R;
import com.simxdeveloper.kamusdicoding.ui.main.fragment.engindo.EngIndoFragment;
import com.simxdeveloper.kamusdicoding.ui.main.fragment.indoeng.IndoEngFragment;

public class MainActivity extends AppCompatActivity
    implements OnNavigationItemSelectedListener {

  public static void start(Context context) {
      Intent starter = new Intent(context, MainActivity.class);
      context.startActivity(starter);
  }

  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @BindView(R.id.nav_view)
  NavigationView navigation;
  @BindView(R.id.drawer_layout)
  DrawerLayout drawer;
  private ActionBarDrawerToggle toggle;
  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_main);
    ButterKnife.bind (this);
    setSupportActionBar (toolbar);
    toggle = new ActionBarDrawerToggle (
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener (toggle);
    toggle.syncState ();
    navigation.setNavigationItemSelectedListener (this);
    loadFragment (IndoEngFragment.newInstance ());
  }

  @Override
  public void onBackPressed () {
    if (drawer.isDrawerOpen (GravityCompat.START)) {
      drawer.closeDrawer (GravityCompat.START);
    } else {
      super.onBackPressed ();
    }
  }

  @Override
  public boolean onCreateOptionsMenu (Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater ().inflate (R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected (MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId ();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected (item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected (MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId ();
    if (id == R.id.nav_indo_eng) {
      loadFragment (IndoEngFragment.newInstance ());
      setTitleBar ("INDO - ENG");
    } else {
      loadFragment (EngIndoFragment.newInstance ());
      setTitleBar ("ENG - INDO");
    }
    drawer.closeDrawer (GravityCompat.START);
    return true;
  }

  private void loadFragment(Fragment fragment){
    FragmentTransaction ft = getSupportFragmentManager ().beginTransaction ();
    ft.replace (R.id.main_fragment,fragment);
    ft.addToBackStack (null);
    ft.commit ();
  }
  private void setTitleBar(String title){
    getSupportActionBar ().setDisplayShowTitleEnabled (true);
    getSupportActionBar ().setTitle (title);
  }

}
