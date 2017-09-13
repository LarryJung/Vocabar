package com.dot2line.vocabar.ui;

import android.os.Bundle;

import com.dot2line.vocabar.R;
import com.dot2line.vocabar.base.BaseMVPActivity;
import com.dot2line.vocabar.presenter.MainPresenter;
import com.dot2line.vocabar.view.MainView;

public class MainActivity extends BaseMVPActivity<MainView, MainPresenter> implements MainView {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  protected MainPresenter createPresenter() {
    return new MainPresenter();
  }

  @Override
  protected MainView createView() {
    return this;
  }
}
