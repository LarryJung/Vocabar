package com.dot2line.vocabar.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class VocaPair extends RealmObject{

  @PrimaryKey
  private long id;
  private String origin;
  private String meaning;

  public VocaPair() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getMeaning() {
    return meaning;
  }

  public void setMeaning(String meaning) {
    this.meaning = meaning;
  }
}
