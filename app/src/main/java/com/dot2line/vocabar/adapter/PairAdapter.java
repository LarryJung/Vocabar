package com.dot2line.vocabar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dot2line.vocabar.R;
import com.dot2line.vocabar.model.VocaPair;

import java.util.ArrayList;

public class PairAdapter extends RecyclerView.Adapter {
  private static final String TAG =  PairAdapter.class.getSimpleName();

  private ArrayList<VocaPair> pairList;

  public PairAdapter() {
    pairList = new ArrayList<>();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.voca_pair_itemview, parent, false);
    return new VocaPairViewHolder(v);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ((VocaPairViewHolder) holder).originTxt.setText(pairList.get(position).getOrigin());
    ((VocaPairViewHolder) holder).meaningTxt.setText(pairList.get(position).getMeaning());
  }

  @Override
  public int getItemCount() {
    return pairList == null ? 0 : pairList.size();
  }

  public ArrayList<VocaPair> getPairList() {
    return pairList;
  }

  public void setPairList(ArrayList<VocaPair> pairList) {
    this.pairList.addAll(pairList);
    notifyDataSetChanged();
  }

  class VocaPairViewHolder extends RecyclerView.ViewHolder {
    TextView originTxt;
    TextView meaningTxt;
    public VocaPairViewHolder(View itemView) {
      super(itemView);
      originTxt = itemView.findViewById(R.id.origin_txt);
      meaningTxt = itemView.findViewById(R.id.meaning_txt);
    }
  }
}
