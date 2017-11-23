package com.dot2line.vocabar.presenter;

import android.text.TextUtils;

import com.dot2line.vocabar.adapter.BookAdapter;
import com.dot2line.vocabar.base.BaseMVPPresenter;
import com.dot2line.vocabar.model.VocaBook;
import com.dot2line.vocabar.model.VocaPair;
import com.dot2line.vocabar.util.CSVReader;
import com.dot2line.vocabar.util.DBUtil;
import com.dot2line.vocabar.view.MainView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
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
    adapter.clear();
    RealmResults<VocaBook> vocaBooks = DBUtil.getVocaBookList(realm);
    for (VocaBook book : vocaBooks) {
      adapter.addBook(book);
    }
  }

  @Override
  protected void onViewDropped() {

  }

  public void addVocaBook(String name, String desc, String path) {
    realm.beginTransaction();
    VocaBook newVocaBook = realm.createObject(VocaBook.class, name.hashCode());
    newVocaBook.setBookName(name);
    newVocaBook.setBookDesc(!TextUtils.isEmpty(desc) ? desc : "");
    newVocaBook.setVocaPairList(getPairListFromCSVFile(path));
    adapter.addBook(newVocaBook);
    realm.commitTransaction();
  }

  private RealmList<VocaPair> getPairListFromCSVFile(String path) {
    RealmList<VocaPair> vocaPairs = new RealmList<>();

    ArrayList<String[]> lines = CSVReader.read(path);

    for (int i = 0; i < lines.size(); i++) {
      VocaPair pair = realm.createObject(VocaPair.class, i);
      pair.setOrigin(lines.get(i)[0]);
      pair.setMeaning(lines.get(i)[1]);
      vocaPairs.add(pair);
    }
    return vocaPairs;
  }

}
