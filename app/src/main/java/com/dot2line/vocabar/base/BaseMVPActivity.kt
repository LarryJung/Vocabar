package com.dot2line.vocabar.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseMVPActivity<V : BaseMVPView, P : BaseMVPPresenter<V>> : AppCompatActivity() {
  lateinit var baseView: V
  lateinit var basePresenter: P

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    basePresenter = createPresenter()
    baseView = createView()
  }

  override fun onStart() {
    super.onStart()

    basePresenter.takeView(baseView)
  }

  override fun onStop() {
    super.onStop()

    basePresenter.dropView(baseView)
  }

  protected abstract fun createPresenter(): P

  protected abstract fun createView(): V
}