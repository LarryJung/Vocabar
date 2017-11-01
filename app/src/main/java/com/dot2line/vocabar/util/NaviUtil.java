package com.dot2line.vocabar.util;

import android.content.Context;
import android.content.Intent;

import com.dot2line.vocabar.MainService;

public class NaviUtil {

  public static void startMainService(Context context) {
    Intent intent = new Intent(context, MainService.class);
    context.startService(intent);
  }

}
