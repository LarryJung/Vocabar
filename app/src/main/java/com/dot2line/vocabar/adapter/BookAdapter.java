package com.dot2line.vocabar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dot2line.vocabar.R;
import com.dot2line.vocabar.model.BookModel;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter {

  private ArrayList<BookModel> bookList;

  public BookAdapter(ArrayList<BookModel> bookList) {
    this.bookList = bookList;
  }

  @Override
  public int getItemViewType(int position) {
    return super.getItemViewType(position);
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_itemview, parent, false);
    return new BookViewHolder(v);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

  }

  public void setBookList(ArrayList<BookModel> bookList) {
    this.bookList.addAll(bookList);
  }

  @Override
  public int getItemCount() {
    return bookList.size();
  }

  class BookViewHolder extends RecyclerView.ViewHolder {
    public BookViewHolder(View itemView) {
      super(itemView);
    }
  }
}
