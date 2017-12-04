package com.dot2line.vocabar.util;

import com.dot2line.vocabar.model.VocaBook;

import org.jetbrains.annotations.NotNull;

import io.realm.Realm;
import io.realm.RealmResults;

public class DBUtil {

  public static final String BOOK_ID = "book_id";

  public static RealmResults<VocaBook> getVocaBookList(Realm realm) {
    return realm.where(VocaBook.class).findAll();
  }

  public static VocaBook getVocaBook(@NotNull Realm realm, @NotNull String bookId) {
    return realm.where(VocaBook.class)
        .equalTo("id", bookId)
        .findFirst();
  }

  public static void removeVocaBook(@NotNull Realm realm, @NotNull String bookId) {
    getVocaBook(realm, bookId).deleteFromRealm();
  }
}
