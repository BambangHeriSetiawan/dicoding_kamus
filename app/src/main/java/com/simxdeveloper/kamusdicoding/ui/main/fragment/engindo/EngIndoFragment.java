package com.simxdeveloper.kamusdicoding.ui.main.fragment.engindo;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.simxdeveloper.kamusdicoding.R;
import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import com.simxdeveloper.kamusdicoding.ui.detail.DetailActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EngIndoFragment extends Fragment implements EngIndoFragmentPresenter {


  @BindView(R.id.search_view)
  SearchView searchView;
  @BindView(R.id.rcv_word)
  RecyclerView rcvWord;
  Unbinder unbinder;
  @BindView(R.id.progress_view)
  ProgressBar progressView;

  public EngIndoFragment () {
  }

  private EngIndoFragmentPresenterImpl presenter;
  private AdapterWordEngIndo adapterWordEngIndo;

  public static EngIndoFragment newInstance () {
    Bundle args = new Bundle ();
    EngIndoFragment fragment = new EngIndoFragment ();
    fragment.setArguments (args);
    return fragment;
  }

  @Override
  public View onCreateView (LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate (R.layout.fragment_eng_indo, container, false);
    unbinder = ButterKnife.bind (this, view);
    presenter = new EngIndoFragmentPresenterImpl (this, getContext ());
    adapterWordEngIndo = new AdapterWordEngIndo (new ArrayList<> (), this);

    return view;
  }

  @Override
  public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated (view, savedInstanceState);
    presenter.loadDataWord ();
    searchView.setOnQueryTextListener (new OnQueryTextListener () {
      @Override
      public boolean onQueryTextSubmit (String query) {
        presenter.queryWord (query);
        return false;
      }

      @Override
      public boolean onQueryTextChange (String newText) {
        presenter.queryWord (newText);
        return false;
      }
    });
    searchView.setOnCloseListener (() -> {
      presenter.loadDataWord ();
      return false;
    });
    rcvWord.setHasFixedSize (true);
    rcvWord.setItemAnimator (new DefaultItemAnimator ());
    rcvWord.setLayoutManager (
        new LinearLayoutManager (getContext (), LinearLayoutManager.VERTICAL, false));
    rcvWord.setAdapter (adapterWordEngIndo);
  }

  @Override
  public void initDataWord (List<WordsEngIndo> wordsEngIndos) {
    adapterWordEngIndo.updateData (wordsEngIndos);
  }

  @Override
  public void showErorr (String message) {
    Toast.makeText (getContext (), message, Toast.LENGTH_SHORT).show ();
  }

  @Override
  public void showDetail (WordsEngIndo wordsEngIndo) {
    DetailActivity.start (getContext (), wordsEngIndo.getWord (), wordsEngIndo.getDesc ());
  }

  @Override
  public void showLoading (boolean show) {
    if (show){
      progressView.setVisibility (View.VISIBLE);
    }else {
      progressView.setVisibility (View.GONE);
    }
  }

  @Override
  public void onDestroyView () {
    super.onDestroyView ();
    unbinder.unbind ();
  }
}
