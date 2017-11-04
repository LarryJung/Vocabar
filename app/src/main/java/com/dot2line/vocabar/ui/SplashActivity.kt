package com.dot2line.vocabar.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.dot2line.vocabar.R
import com.dot2line.vocabar.util.NaviUtil
import com.facebook.shimmer.ShimmerFrameLayout

class SplashActivity : AppCompatActivity() {
  private val SPLASH_TIME = 1000L
  private val REQ_OVERLAY_PERMISSION = 1203


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_splash)

    startShimmer()
  }

  private fun startShimmer() {
    val shimmer: ShimmerFrameLayout = findViewById(R.id.shimmer) as ShimmerFrameLayout
    shimmer.angle = ShimmerFrameLayout.MaskAngle.CW_0
    shimmer.startShimmerAnimation()

    Handler().postDelayed({
      next()
    }, SPLASH_TIME)
  }


  @RequiresApi(Build.VERSION_CODES.M)
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    when (requestCode) {
      REQ_OVERLAY_PERMISSION -> {
        if (Settings.canDrawOverlays(this)) {
          NaviUtil.moveToMainActivity(this)
        } else {
          Toast.makeText(this, R.string.should_overlay_permission, Toast.LENGTH_LONG).show()
        }
        finish()
      }
    }
  }

  private fun next() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        && !Settings.canDrawOverlays(this)) {
        NaviUtil.startOverlayWindowService(this, REQ_OVERLAY_PERMISSION)
    } else {
      NaviUtil.moveToMainActivity(this)
      finish()
    }
  }
}