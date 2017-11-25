package com.dot2line.vocabar.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class VocaBook extends RealmObject {

  @PrimaryKey
  @Required
  private String id;
  private String bookName;
  private RealmList<VocaPair> vocaPairList;

  public VocaBook() {
  }

  public VocaBook(String id, String bookName, RealmList<VocaPair> vocaPairList) {
    this.id = id;
    this.bookName = bookName;
    this.vocaPairList = vocaPairList;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public RealmList<VocaPair> getVocaPairList() {
    return vocaPairList;
  }

  public void setVocaPairList(RealmList<VocaPair> vocaPairList) {
    this.vocaPairList = vocaPairList;
  }
}
