package com.dot2line.vocabar.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dot2line.vocabar.MDialogFragment;
import com.dot2line.vocabar.R;
import com.dot2line.vocabar.adapter.BookAdapter;
import com.dot2line.vocabar.base.BaseMVPActivity;
import com.dot2line.vocabar.model.VocaBook;
import com.dot2line.vocabar.model.VocaPair;
import com.dot2line.vocabar.presenter.MainPresenter;
import com.dot2line.vocabar.util.NaviUtil;
import com.dot2line.vocabar.view.MainView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import io.realm.Realm;

public class MainActivity extends BaseMVPActivity<MainView, MainPresenter>
    implements MainView, BookAdapter.OnClickEmptyViewListner, MDialogFragment.VCBDialogListener {
  private static final String TAG = MainActivity.class.getSimpleName();
  private static final String CSV_FILES = "CSV_FILES";
  private static final String CSV_FORMAT = ".csv";

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

  String tempFilesPath;

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
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
      case FilePickerConst.REQUEST_CODE_DOC:
        if (resultCode == Activity.RESULT_OK && data != null) {
          ArrayList<String> filePaths = new ArrayList<>();
          filePaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS));
          tempFilesPath = filePaths.get(0);
          showMakeBookDialog();
        }
        break;
      default:
        break;
    }
  }

  @Override
  public void onClickBookItemView(VocaBook book) {
    String output = "";
    for (VocaPair pair : book.getVocaPairList()) {
      output += pair.getOrigin() + " -> " + pair.getMeaning() + "\n";
    }

    // TEST CODE
    Toast.makeText(getApplicationContext()
        , output
        , Toast.LENGTH_LONG).show();

  }

  @Override
  public void onClickEmptyView(View v) {
    String[] format = {CSV_FORMAT};
    FilePickerBuilder.getInstance().setMaxCount(1)
        .setActivityTheme(R.style.AppTheme)
        .enableDocSupport(false)
        .addFileSupport(CSV_FILES, format, R.drawable.ic_csv_file)
        .pickFile(this);
  }

  @Override
  public void onDialogPositiveClick(DialogFragment fragment, String bookName, String bookDesc) {
    basePresenter.addVocaBook(bookName, bookDesc, tempFilesPath);
  }

  private void showMakeBookDialog() {
    MDialogFragment dialog = new MDialogFragment();
    dialog.show(getSupportFragmentManager(), MDialogFragment.TAG);
  }

}
