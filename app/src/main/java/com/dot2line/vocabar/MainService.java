package com.dot2line.vocabar;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainService extends Service {

  private TextView txtVoca;

  private View loBar;
  private WindowManager wm;
  private WindowManager.LayoutParams wmParams;
  private View.OnTouchListener onBarTouchListener;

  private boolean isMove = false;
  private float touchX, touchY;
  private int viewX, viewY;

  int index;

  @Override
  public void onCreate() {
    super.onCreate();

  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    startInflate();

    return super.onStartCommand(intent, flags, startId);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();

    finishInflate();
  }

  @Override
  public IBinder onBind(Intent intent) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  private void startInflate() {
    initTouchListener();

    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    loBar = inflater.inflate(R.layout.layout_vocabar, null);
    loBar.setOnTouchListener(onBarTouchListener);
    txtVoca = (TextView) loBar.findViewById(R.id.txt_voca);

    initWmParams();

    wm = (WindowManager) getSystemService(WINDOW_SERVICE);
    wm.addView(loBar, wmParams);
  }

  private void finishInflate() {
    if (loBar != null) {
      wm.removeView(loBar);
      loBar = null;
    }
  }

  private void initWmParams() {
    wmParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
    wmParams.gravity = Gravity.TOP | Gravity.LEFT;
  }

  private void initTouchListener() {
    onBarTouchListener = new View.OnTouchListener() {
       @Override
       public boolean onTouch(View view, MotionEvent event) {
         switch (event.getAction()) {
           case MotionEvent.ACTION_DOWN:
             isMove = false;
             touchX = event.getRawX();
             touchY = event.getRawY();
             viewX = wmParams.x;
             viewY = wmParams.y;
             break;
           case MotionEvent.ACTION_UP:
             if (!isMove) {
               // TEST_CODE (Tab)
               // if (index == vocaList.size()) {
               //   index = 0;
               // }
               // changeWord();
               index++;
             }
             break;
           case MotionEvent.ACTION_MOVE:
             isMove = true;

             int x = (int) (event.getRawX() - touchX);
             int y = (int) (event.getRawY() - touchY);

             final int num = 5;
             if ((x > -num && x < num) && (y > -num && y < num)) {
               isMove = false;
               break;
             }

             wmParams.x = viewX + x;
             wmParams.y = viewY + y;

             wm.updateViewLayout(view, wmParams);

             break;
         }
         return true;
       }
    };
  }
}
