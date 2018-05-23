package com.simxdeveloper.kamusdicoding.ui.main.fragment.indoeng;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SearchView.OnSuggestionListener;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.simxdeveloper.kamusdicoding.R;
import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import com.simxdeveloper.kamusdicoding.ui.detail.DetailActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndoEngFragment extends Fragment implements IndoEngFragmentPresenter {


  @BindView(R.id.search_view)
  SearchView searchView;
  @BindView(R.id.rcv_word)
  RecyclerView rcvWord;
  Unbinder unbinder;

  public IndoEngFragment () {
    // Required empty public constructor
  }

  private IndoEngFragmentPresenterImpl presenter;
  private AdapterWordIndoEng adapterWordIndoEng;

  public static IndoEngFragment newInstance () {
    Bundle args = new Bundle ();
    IndoEngFragment fragment = new IndoEngFragment ();
    fragment.setArguments (args);
    return fragment;
  }

  @Override
  public View onCreateView (LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate (R.layout.fragment_indo_eng, container, false);
    unbinder = ButterKnife.bind (this, view);
    presenter = new IndoEngFragmentPresenterImpl (getContext (),this);
    adapterWordIndoEng = new AdapterWordIndoEng (new ArrayList<> (),this);
    return view;
  }

  @Override
  public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated (view, savedInstanceState);
    presenter.getDataWordIndoEngl();
    searchView.setOnQueryTextListener (new OnQueryTextListener () {
      @Override
      public boolean onQueryTextSubmit (String query) {
        Log.e ("IndoEngFragment", "onQueryTextSubmit: " + query);
        presenter.queryWord(query);
        return false;
      }

      @Override
      public boolean onQueryTextChange (String newText) {
        presenter.queryWord (newText);
        return false;
      }
    });
    searchView.setOnCloseListener (() -> {
      presenter.getDataWordIndoEngl ();
      return false;
    });
    rcvWord.setHasFixedSize (true);
    rcvWord.setItemAnimator (new DefaultItemAnimator ());
    rcvWord.setLayoutManager (new LinearLayoutManager (getContext (),LinearLayoutManager.VERTICAL,false));
    rcvWord.setAdapter (adapterWordIndoEng);
  }

  @Override
  public void initDataWord (List<WordsIndoEng> wordsIndoEngs) {
    adapterWordIndoEng.updateData (wordsIndoEngs);
  }

  @Override
  public void showError (String message) {
    Toast.makeText (getContext (),message,Toast.LENGTH_SHORT).show ();
  }

  @Override
  public void showDetail (WordsIndoEng wordsIndoEng) {
    Log.e ("IndoEngFragment", "showDetail: " + wordsIndoEng);
    DetailActivity.start (getContext (),wordsIndoEng.getWord (),wordsIndoEng.getDesc ());
  }

  @Override
  public void onDestroyView () {
    super.onDestroyView ();
    unbinder.unbind ();
  }
}
