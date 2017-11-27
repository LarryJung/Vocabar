package com.dot2line.vocabar.base;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import io.realm.Realm;

public class BaseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Fabric.with(this, new Crashlytics());

    Realm.init(this);
  }
}
