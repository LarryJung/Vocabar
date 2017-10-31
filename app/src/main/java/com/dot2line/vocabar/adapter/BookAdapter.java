package com.dot2line.vocabar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dot2line.vocabar.R;
import com.dot2line.vocabar.model.BookModel;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter {

  private final static int EMPTY_VIEW = 0;
  private final static int NORMAL_VIEW = 1;

  private ArrayList<BookModel> bookList;

  public BookAdapter(ArrayList<BookModel> bookList) {
    this.bookList = bookList;
  }

  @Override
  public int getItemViewType(int position) {
    if (bookList.get(position).isEmpty()) {
      return EMPTY_VIEW;
    } else {
      return NORMAL_VIEW;
    }
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v;
    switch (viewType) {
      case EMPTY_VIEW:
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_empty_itemview, parent, false);
        break;
      case NORMAL_VIEW:
      default:
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_itemview, parent, false);
    }

    return new BookViewHolder(v);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    switch (getItemViewType(position)) {
      case EMPTY_VIEW:
      default:
        break;
      case NORMAL_VIEW:
        if (holder instanceof BookViewHolder) {
          ((BookViewHolder) holder).bookName.setText(bookList.get(position).getBookName());
        }
        break;
    }
  }

  public void setBookList(ArrayList<BookModel> bookList) {
    this.bookList.addAll(bookList);
  }

  @Override
  public int getItemCount() {
    return bookList.size();
  }

  class BookViewHolder extends RecyclerView.ViewHolder {
    TextView bookName;
    public BookViewHolder(View itemView) {
      super(itemView);
      bookName = (TextView) itemView.findViewById(R.id.txt_book_name);
    }
  }

  class EmptyViewHolder extends RecyclerView.ViewHolder {
    public EmptyViewHolder(View itemView) {
      super(itemView);
    }
  }
}
