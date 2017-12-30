package com.dot2line.vocabar.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.dot2line.vocabar.MDialogFragment;
import com.dot2line.vocabar.R;
import com.dot2line.vocabar.adapter.BookAdapter;
import com.dot2line.vocabar.base.BaseMVPActivity;
import com.dot2line.vocabar.model.VocaBook;
import com.dot2line.vocabar.presenter.MainPresenter;
import com.dot2line.vocabar.util.DBUtil;
import com.dot2line.vocabar.util.NaviUtil;
import com.dot2line.vocabar.view.MainView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import io.realm.Realm;

public class MainActivity extends BaseMVPActivity<MainView, MainPresenter>
    implements MainView, BookAdapter.OnClickItemViewListener, MDialogFragment.VCBDialogListener {
  private static final String TAG = MainActivity.class.getSimpleName();
  private static final String CSV_FILES = "CSV_FILES";
  private static final String[] CSV_FORMAT = {".csv"};

  private static final int REQ_CODE_READ_EXTERNAL_STORAGE_PERMISSION = 1234;

  @BindView(R.id.recycler_view)
  RecyclerView bookRecyclerView;
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

    bookRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    bookRecyclerView.setAdapter(adapter);

    stopBtn.setOnClickListener(v -> NaviUtil.INSTANCE.stopMainService(getApplicationContext()));

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
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
      case REQ_CODE_READ_EXTERNAL_STORAGE_PERMISSION:
        if (grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          moveToFilePicker();
        }
        break;
    }
  }

  @Override
  public void onClickBookItemView(VocaBook book) {
    Intent i = new Intent(this, DetailActivity.class) ;
    i.putExtra(DBUtil.INSTANCE.getBOOK_ID(), book.getId());
    startActivity(i);
  }

  @Override
  public void onLongClickBookItemView(String bookId, int index) {
    AlertDialog dialog = new AlertDialog.Builder(this)
        .setTitle(R.string.title_delete)
        .setPositiveButton(R.string.delete, (dialogInterface, i) -> {
          getBasePresenter().removeBook(bookId);
          adapter.removeBook(index);
        })
        .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
          dialogInterface.dismiss();
        })
        .setCancelable(true)
        .create();
    dialog.show();
  }

  @Override
  public void onClickEmptyView(View v) {
    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
        == PackageManager.PERMISSION_GRANTED) {
      moveToFilePicker();
    } else {
      ActivityCompat.requestPermissions(MainActivity.this,
          new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
          REQ_CODE_READ_EXTERNAL_STORAGE_PERMISSION);
    }
  }

  @Override
  public void onDialogPositiveClick(DialogFragment fragment, String bookName) {
    getBasePresenter().addVocaBook(bookName, tempFilesPath);
  }

  private void showMakeBookDialog() {
    MDialogFragment dialog = new MDialogFragment();
    dialog.show(getSupportFragmentManager(), MDialogFragment.Companion.getTAG());
  }

  private void moveToFilePicker() {
    FilePickerBuilder.getInstance()
        .setMaxCount(1)
        .setActivityTheme(R.style.Theme_AppCompat_Light_NoActionBar)
        .enableDocSupport(false)
        .addFileSupport(CSV_FILES, CSV_FORMAT, R.drawable.ic_csv_file)
        .pickFile(this);
  }
}
