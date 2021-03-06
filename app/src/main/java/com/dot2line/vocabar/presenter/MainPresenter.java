package com.dot2line.vocabar.presenter;

import android.support.v4.util.Pair;

import com.dot2line.vocabar.adapter.BookAdapter;
import com.dot2line.vocabar.base.BaseMVPPresenter;
import com.dot2line.vocabar.model.VocaBook;
import com.dot2line.vocabar.model.VocaPair;
import com.dot2line.vocabar.util.CSVReader;
import com.dot2line.vocabar.util.DBUtil;
import com.dot2line.vocabar.view.MainView;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainPresenter extends BaseMVPPresenter<MainView> {
  private static final String TAG = MainPresenter.class.getSimpleName();

  private BookAdapter adapter;
  private Realm realm;

  public MainPresenter(Realm realm, BookAdapter adapter) {
    this.realm = realm;
    this.adapter = adapter;

    this.realm.setDefaultConfiguration(new RealmConfiguration.Builder().build());
    this.realm = Realm.getDefaultInstance();
  }

  @Override
  protected void onViewTaken() {
    adapter.clear();
    RealmResults<VocaBook> vocaBooks = DBUtil.INSTANCE.getVocaBookList(realm);
    for (VocaBook book : vocaBooks) {
      adapter.addBook(book);
    }
  }

  @Override
  protected void onViewDropped() {

  }

  public void addVocaBook(String name, String path) {
    realm.beginTransaction();
    VocaBook newVocaBook = realm.createObject(VocaBook.class, UUID.randomUUID().toString());
    newVocaBook.setBookName(name);
    newVocaBook.setVocaPairList(getPairListFromCSVFile(path));
    adapter.addBook(newVocaBook);
    realm.commitTransaction();
  }

  public void removeBook(String bookId) {
    realm.beginTransaction();
    DBUtil.INSTANCE.removeVocaBook(realm, bookId);
    realm.commitTransaction();
  }

  private RealmList<VocaPair> getPairListFromCSVFile(String path) {
    RealmList<VocaPair> vocaPairs = new RealmList<>();

    List<Pair<String, String>> lines = CSVReader.read(path);

    for (int i = 0; i < lines.size(); i++) {
      VocaPair pair = realm.createObject(VocaPair.class);
      pair.setId(i);
      pair.setOrigin(lines.get(i).first);
      pair.setMeaning(lines.get(i).second.trim());
      vocaPairs.add(pair);
    }
    return vocaPairs;
  }

}
