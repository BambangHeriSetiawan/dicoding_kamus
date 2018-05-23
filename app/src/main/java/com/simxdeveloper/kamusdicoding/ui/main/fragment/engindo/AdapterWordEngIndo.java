package com.simxdeveloper.kamusdicoding.ui.main.fragment.engindo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.simxdeveloper.kamusdicoding.R;
import com.simxdeveloper.kamusdicoding.data.entity.WordsEngIndo;
import com.simxdeveloper.kamusdicoding.ui.main.fragment.engindo.AdapterWordEngIndo.Holder;
import java.util.List;

/**
 * User: simx Date: 24/05/18 2:59
 */
public class AdapterWordEngIndo extends Adapter<Holder> {

  private List<WordsEngIndo> wordsEngIndos;
  private EngIndoFragmentPresenter presenter;

  public AdapterWordEngIndo (
      List<WordsEngIndo> wordsEngIndos,
      EngIndoFragmentPresenter presenter) {
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
    WordsEngIndo wordsEngIndo = getItem (position);
    holder.tvWord.setText (wordsEngIndo.getWord ());
    holder.tvWordDesc.setText (wordsEngIndo.getDesc ());
    holder.itemView.setOnClickListener (v -> {
      presenter.showDetail(wordsEngIndo);
    });
  }

  @Override
  public int getItemCount () {
    return wordsEngIndos.size ();
  }

  private WordsEngIndo getItem (int pos) {
    return wordsEngIndos.get (pos);
  }

  public void updateData (List<WordsEngIndo> wordsEngIndos) {
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
