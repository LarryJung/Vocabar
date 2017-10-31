package com.dot2line.vocabar.model;

public class BookModel {

  private boolean isEmpty;
  private String bookName;

  public BookModel(boolean isEmpty) {
    this.isEmpty = isEmpty;
  }

  public BookModel(boolean isEmpty, String bookName) {
    this.isEmpty = isEmpty;
    this.bookName = bookName;
  }

  public boolean isEmpty() {
    return isEmpty;
  }

  public void setEmpty(boolean empty) {
    isEmpty = empty;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }
}
