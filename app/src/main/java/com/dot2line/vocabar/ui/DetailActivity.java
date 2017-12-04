package com.dot2line.vocabar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.dot2line.vocabar.MainService;
import com.dot2line.vocabar.R;
import com.dot2line.vocabar.adapter.PairAdapter;
import com.dot2line.vocabar.base.BaseMVPActivity;
import com.dot2line.vocabar.model.VocaPair;
import com.dot2line.vocabar.presenter.DetailPresenter;
import com.dot2line.vocabar.util.DBUtil;
import com.dot2line.vocabar.view.DetailView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class DetailActivity extends BaseMVPActivity<DetailView, DetailPresenter> implements DetailView {
  private static final String TAG = DetailActivity.class.getSimpleName();

  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;
  @BindView(R.id.btn_start)
  Button startBtn;

  Realm realm;
  PairAdapter adapter;
  String bookId;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    overridePendingTransition(R.anim.start_enter, R.anim.start_exit);

    setContentView(R.layout.activity_detail);

    realm.init(this);

    ButterKnife.bind(this);

    adapter = new PairAdapter();
    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    recyclerView.setAdapter(adapter);

    startBtn.setOnClickListener(view -> {
      Intent intent = new Intent(getApplicationContext(), MainService.class);
      intent.putExtra(DBUtil.BOOK_ID, bookId);
      startService(intent);
    });
  }

  @Override
  protected DetailPresenter createPresenter() {
    bookId = getIntent().getStringExtra(DBUtil.BOOK_ID);
    if (bookId == null) {
      finish();
    }
    return new DetailPresenter(realm, bookId);
  }

  @Override
  protected DetailView createView() {
    return this;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onDestroy() {
    if (realm != null) {
      realm.close();
    }
    super.onDestroy();
  }

  @Override
  public void finish() {
    super.finish();
    this.overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
  }

  @Override
  public void setBookTitle(String bookTitle) {
    setDetailActionBar(bookTitle);
  }

  private void setDetailActionBar(String title) {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    setTitle(title);
  }

  @Override
  public void setPairList(ArrayList<VocaPair> pairs) {
    adapter.setPairList(pairs);
  }
}
