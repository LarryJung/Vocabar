package com.dot2line.vocabar.presenter;

import com.dot2line.vocabar.base.BaseMVPPresenter;
import com.dot2line.vocabar.model.VocaBook;
import com.dot2line.vocabar.model.VocaPair;
import com.dot2line.vocabar.util.DBUtil;
import com.dot2line.vocabar.view.DetailView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailPresenter extends BaseMVPPresenter<DetailView> {
  private static final String TAG = DetailPresenter.class.getSimpleName();

  private Realm realm;
  private String bookId;
  private VocaBook vocaBook;

  public DetailPresenter(Realm realm, @NotNull String bookId) {
    this.realm = realm;
    this.bookId = bookId;

    this.realm.setDefaultConfiguration(new RealmConfiguration.Builder().build());
    this.realm = Realm.getDefaultInstance();
  }

  @Override
  protected void onViewTaken() {
    vocaBook = DBUtil.INSTANCE.getVocaBook(realm, bookId);
    getView().setBookTitle(vocaBook.getBookName());
    getView().setPairList((ArrayList<VocaPair>) realm.copyFromRealm(vocaBook.getVocaPairList()));
  }

  @Override
  protected void onViewDropped() {

  }
}
