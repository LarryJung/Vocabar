package com.dot2line.vocabar.presenter;

import android.text.TextUtils;

import com.dot2line.vocabar.adapter.BookAdapter;
import com.dot2line.vocabar.base.BaseMVPPresenter;
import com.dot2line.vocabar.model.VocaBook;
import com.dot2line.vocabar.util.DBUtil;
import com.dot2line.vocabar.view.MainView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainPresenter extends BaseMVPPresenter<MainView> {
  private static final String TAG = MainPresenter.class.getSimpleName();

  BookAdapter adapter;
  Realm realm;

  public MainPresenter(Realm realm, BookAdapter adapter) {
    this.realm = realm;
    this.adapter = adapter;

    this.realm.setDefaultConfiguration(new RealmConfiguration.Builder().build());
    this.realm = Realm.getDefaultInstance();
  }

  @Override
  protected void onViewTaken() {
    RealmResults<VocaBook> vocaBooks = DBUtil.getVocaBookList(realm);
    for (VocaBook book : vocaBooks) {
      adapter.addBook(book);
    }
  }

  @Override
  protected void onViewDropped() {

  }

  public void addVocaBook(String name, String desc) {
    realm.beginTransaction();
    VocaBook newVocaBook = realm.createObject(VocaBook.class, name.hashCode());
    newVocaBook.setBookName(name);
    newVocaBook.setBookDesc(!TextUtils.isEmpty(desc) ? desc : "");
    adapter.addBook(newVocaBook);
    realm.commitTransaction();
  }
}
