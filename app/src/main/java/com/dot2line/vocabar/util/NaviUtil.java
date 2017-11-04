package com.dot2line.vocabar.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import com.dot2line.vocabar.MainService;
import com.dot2line.vocabar.ui.MainActivity;

public class NaviUtil {

  public static void startMainService(Context context) {
    Intent intent = new Intent(context, MainService.class);
    context.startService(intent);
  }

  public static void startOverlayWindowService(AppCompatActivity appCompatActivity, int requestCode) {
    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
        Uri.parse("package:" + appCompatActivity.getApplicationContext().getPackageName()));
    appCompatActivity.startActivityForResult(intent, requestCode);
  }

  public static void moveToMainActivity(Context context) {
    Intent intent = new  Intent(context, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    context.startActivity(intent);
  }
}
