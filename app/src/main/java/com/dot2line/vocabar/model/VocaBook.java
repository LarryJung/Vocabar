package com.dot2line.vocabar.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class VocaBook extends RealmObject {

  @PrimaryKey
  private long id;
  private String bookName;
  private String bookDesc;
  private RealmList<VocaPair> vocaPairList;

  public VocaBook() {
  }

  public VocaBook(long id, String bookName, String bookDesc, RealmList<VocaPair> vocaPairList) {
    this.id = id;
    this.bookName = bookName;
    this.bookDesc = bookDesc;
    this.vocaPairList = vocaPairList;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public String getBookDesc() {
    return bookDesc;
  }

  public void setBookDesc(String bookDesc) {
    this.bookDesc = bookDesc;
  }

  public RealmList<VocaPair> getVocaPairList() {
    return vocaPairList;
  }

  public void setVocaPairList(RealmList<VocaPair> vocaPairList) {
    this.vocaPairList = vocaPairList;
  }
}
