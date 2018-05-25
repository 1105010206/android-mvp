package com.android.mvp.demo.presentor;

import android.view.KeyEvent;

import com.android.mvp.demo.presentor.base.BaseActivityPresentorI;
import com.android.mvp.demo.ui.viewinterface.StartUpActivityViewInterface;

/**
 * Created by Administrator on 2017/11/24 0024.
 */

public interface StartUpActivityPresentorI<V extends StartUpActivityViewInterface> extends BaseActivityPresentorI {
    public boolean onViewKeyDown(int keyCode, KeyEvent event);
    public void onViewCreated();
    public void startUpPushService();
}
