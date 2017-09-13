package com.dot2line.vocabar.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public abstract class BaseMVPPresenter<V extends BaseMVPView> {
  private WeakReference<V> view = null;

  private boolean loaded;

  public final void takeView(V view) {
    if (view == null) throw new NullPointerException("new view must not be null");

    if (this.view != null) dropView(this.view.get());

    this.view = new WeakReference<V>(view);
    if (!loaded) {
      loaded = true;
      onViewTaken();
    }
  }

  public final void dropView(V view) {
    if (view == null) throw new NullPointerException("dropped view must not be null");
    loaded = false;
    if (this.view != null) {
      this.view.clear();
      this.view = null;
    }

    onViewDropped();
  }

  protected final V getView() {
    if (view == null) throw new NullPointerException("getView called when view is null. Ensure takeView(View view) is called first.");
    return view.get();
  }

  public boolean isViewAttached() {
    return view != null && view.get() != null;
  }

  protected abstract void onViewTaken();

  protected abstract void onViewDropped();
}
