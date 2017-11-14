package com.dot2line.vocabar.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.dot2line.vocabar.DialogFragment;
import com.dot2line.vocabar.R;
import com.dot2line.vocabar.adapter.BookAdapter;
import com.dot2line.vocabar.base.BaseMVPActivity;
import com.dot2line.vocabar.presenter.MainPresenter;
import com.dot2line.vocabar.util.NaviUtil;
import com.dot2line.vocabar.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends BaseMVPActivity<MainView, MainPresenter>
    implements MainView, BookAdapter.OnClickEmptyViewListner, DialogFragment.VCBDialogListener {

  @BindView(R.id.recycler_view)
  RecyclerView bookRecyclerView;
  @BindView(R.id.test_button)
  Button startBtn;
  @BindView(R.id.test_button_2)
  Button stopBtn;
  @BindView(R.id.toolbar)
  Toolbar mToolbar;

  Realm realm;
  BookAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    realm.init(this);


    ButterKnife.bind(this);
    setSupportActionBar(mToolbar);

    bookRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    bookRecyclerView.setAdapter(adapter);

    // TODO : To Remove
    startBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        NaviUtil.INSTANCE.startMainService(getApplicationContext());
      }
    });

    stopBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        NaviUtil.INSTANCE.stopMainService(getApplicationContext());
      }
    });

  }

  @Override
  protected MainPresenter createPresenter() {
    adapter = new BookAdapter(getApplicationContext(), this);
    return new MainPresenter(realm, adapter);
  }

  @Override
  protected MainView createView() {
    return this;
  }

  @Override
  protected void onDestroy() {
    if (realm != null) {
      realm.close();
    }
    super.onDestroy();
  }

  @Override
  public void onClickEmptyView(View v) {
    DialogFragment dialog = new DialogFragment();
    dialog.show(getSupportFragmentManager(), "VCBDialogFragment");
  }

  @Override
  public void onDialogPositiveClick(android.support.v4.app.DialogFragment dfragment, String bookName, String bookDesc) {
    basePresenter.addVocaBook(bookName, bookDesc);
  }

}
