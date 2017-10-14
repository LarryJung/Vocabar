package com.dot2line.vocabar.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dot2line.vocabar.R;
import com.dot2line.vocabar.adapter.BookAdapter;
import com.dot2line.vocabar.base.BaseMVPActivity;
import com.dot2line.vocabar.model.BookModel;
import com.dot2line.vocabar.presenter.MainPresenter;
import com.dot2line.vocabar.view.MainView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseMVPActivity<MainView, MainPresenter> implements MainView {

  @BindView(R.id.recycler_view)
  RecyclerView bookRecyclerView;

  BookAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);


    ArrayList<BookModel> bookList = new ArrayList();
    bookList.add(new BookModel());
    bookList.add(new BookModel());
    bookList.add(new BookModel());
    bookList.add(new BookModel());
    bookList.add(new BookModel());
    bookList.add(new BookModel());
    bookList.add(new BookModel());
    bookList.add(new BookModel());
    adapter = new BookAdapter(bookList);

    bookRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    bookRecyclerView.setAdapter(adapter);
  }

  @Override
  protected MainPresenter createPresenter() {
    return new MainPresenter();
  }

  @Override
  protected MainView createView() {
    return this;
  }
}
