package com.simxdeveloper.kamusdicoding.ui.main.fragment.indoeng;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.simxdeveloper.kamusdicoding.R;
import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import com.simxdeveloper.kamusdicoding.data.entity.WordsIndoEng;
import com.simxdeveloper.kamusdicoding.ui.main.fragment.engindo.AdapterWordEngIndo.Holder;
import com.simxdeveloper.kamusdicoding.ui.main.fragment.engindo.EngIndoFragmentPresenter;
import java.util.List;

/**
 * User: simx Date: 24/05/18 2:59
 */
public class AdapterWordIndoEng extends Adapter<AdapterWordIndoEng.Holder> {

  private List<WordsIndoEng> wordsEngIndos;
  private IndoEngFragmentPresenter presenter;

  public AdapterWordIndoEng (
      List<WordsIndoEng> wordsEngIndos,
      IndoEngFragmentPresenter presenter) {
    this.wordsEngIndos = wordsEngIndos;
    this.presenter = presenter;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
    return new Holder (
        LayoutInflater.from (parent.getContext ()).inflate (R.layout.item_word, parent, false));
  }

  @Override
  public void onBindViewHolder (@NonNull Holder holder, int position) {
    WordsIndoEng wordsIndoEng = getItem (position);
    holder.tvWord.setText (wordsIndoEng.getWord ());
    holder.tvWordDesc.setText (wordsIndoEng.getDesc ());
    holder.itemView.setOnClickListener (v -> {
      presenter.showDetail(wordsIndoEng);
    });
  }

  @Override
  public int getItemCount () {
    return wordsEngIndos.size ();
  }

  private WordsIndoEng getItem (int pos) {
    return wordsEngIndos.get (pos);
  }

  public void updateData (List<WordsIndoEng> wordsEngIndos) {
    this.wordsEngIndos = wordsEngIndos;
    notifyDataSetChanged ();
  }

  public class Holder extends ViewHolder {
    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.tv_word_desc)
    TextView tvWordDesc;
    public Holder (View itemView) {
      super (itemView);
      ButterKnife.bind (this,itemView);
    }
  }
}
