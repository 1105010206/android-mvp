package com.android.mvp.demo.ui.viewinterface.base;

import android.app.Activity;

import com.android.mvp.demo.presentor.base.BaseActivityPresentorI;
import com.android.mvp.demo.presentor.base.BaseFragmentPresentorI;


/**
 * 作者: 刘康
 * 时间: 2017/12/26 0026 15:14
 */

public interface BaseFragmentViewInterface {
    public Activity getFGActivity();

    public BaseActivityPresentorI getActivityPresentor();

    public BaseFragmentPresentorI getCurrentPresentor();
}
