package com.dot2line.vocabar.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseMVPActivity<V extends BaseMVPView, P extends BaseMVPPresenter<V>> extends AppCompatActivity {
    protected V baseView;
    protected P basePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        basePresenter = createPresenter();
        baseView = createView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        basePresenter.takeView(baseView);
    }

    @Override
    protected void onStop() {
        super.onStop();

        basePresenter.dropView(baseView);
    }

    protected abstract P createPresenter();

    protected abstract V createView();
}