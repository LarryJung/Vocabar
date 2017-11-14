package com.dot2line.vocabar.util;

import com.dot2line.vocabar.model.VocaBook;

import io.realm.Realm;
import io.realm.RealmResults;

public class DBUtil {

  public static RealmResults<VocaBook> getVocaBookList(Realm realm) {
    return realm.where(VocaBook.class).findAll();
  }
}
