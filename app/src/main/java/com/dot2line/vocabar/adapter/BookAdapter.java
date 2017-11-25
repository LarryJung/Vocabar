package com.dot2line.vocabar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dot2line.vocabar.R;
import com.dot2line.vocabar.model.VocaBook;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter {
  private static final String TAG = BookAdapter.class.getSimpleName();

  private final static int EMPTY_VIEW = 0;
  private final static int NORMAL_VIEW = 1;

  private ArrayList<VocaBook> bookList;
  private Context context;

  OnClickEmptyViewListner listener;

  public BookAdapter(Context context, OnClickEmptyViewListner listener) {
    this.listener = listener;
    this.context = context;
    this.bookList = new ArrayList<>();
  }

  public void addBook(VocaBook book) {
    this.bookList.add(book);
    notifyDataSetChanged();
  }

  public void clear() {
    this.bookList.clear();
  }

  @Override
  public int getItemViewType(int position) {
    if (position == bookList.size()) {
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
        return new EmptyViewHolder(v);
      case NORMAL_VIEW:
      default:
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_itemview, parent, false);
        return new BookViewHolder(v);
    }
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
    switch (getItemViewType(position)) {
      case EMPTY_VIEW:
      default:
        if (holder instanceof EmptyViewHolder) {
          ((EmptyViewHolder) holder).root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              listener.onClickEmptyView(v);
            }
          });
        }
        break;
      case NORMAL_VIEW:
        if (holder instanceof BookViewHolder) {
          ((BookViewHolder) holder).root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              VocaBook book = bookList.get(position);
              listener.onClickBookItemView(book);
            }
          });
          ((BookViewHolder) holder).bookName.setText(bookList.get(position).getBookName());
        }
        break;
    }
  }

  @Override
  public int getItemCount() {
    return bookList == null ? 0 : bookList.size() + 1;
  }

  class BookViewHolder extends RecyclerView.ViewHolder {
    View root;
    TextView bookName;
    public BookViewHolder(View itemView) {
      super(itemView);
      root = itemView;
      bookName = itemView.findViewById(R.id.txt_book_name);
    }
  }

  class EmptyViewHolder extends RecyclerView.ViewHolder {
    View root;
    public EmptyViewHolder(View itemView) {
      super(itemView);
      root = itemView;
    }
  }

  public interface OnClickEmptyViewListner {
    void onClickEmptyView(View v);
    void onClickBookItemView(VocaBook book);
  }
}
