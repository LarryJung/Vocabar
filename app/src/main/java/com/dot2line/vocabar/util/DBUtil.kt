package com.dot2line.vocabar.util

import com.dot2line.vocabar.model.VocaBook

import io.realm.Realm
import io.realm.RealmResults

object DBUtil {

  val BOOK_ID = "book_id"

  fun getVocaBookList(realm: Realm): RealmResults<VocaBook> {
    return realm.where(VocaBook::class.java).findAll()
  }

  fun getVocaBook(realm: Realm, bookId: String): VocaBook? {
    return realm.where(VocaBook::class.java)
        .equalTo("id", bookId)
        .findFirst()
  }

  fun removeVocaBook(realm: Realm, bookId: String) {
    getVocaBook(realm, bookId)?.deleteFromRealm();
  }
}
