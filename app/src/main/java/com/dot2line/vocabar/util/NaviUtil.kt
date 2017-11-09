package com.dot2line.vocabar.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.support.v7.app.AppCompatActivity

import com.dot2line.vocabar.MainService
import com.dot2line.vocabar.ui.MainActivity

object NaviUtil {

  // for Activity
  fun startOverlayPermissionActivityForResult(appCompatActivity: AppCompatActivity, requestCode: Int) {
    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
        Uri.parse("package:" + appCompatActivity.applicationContext.packageName))
    appCompatActivity.startActivityForResult(intent, requestCode)
  }

  fun moveToMainActivity(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    context.startActivity(intent)
  }

  // for Service
  fun startMainService(context: Context) {
    val intent = Intent(context, MainService::class.java)
    context.startService(intent)
  }

  fun stopMainService(context: Context) {
    val intent = Intent(context, MainService::class.java)
    context.stopService(intent)
  }
}
