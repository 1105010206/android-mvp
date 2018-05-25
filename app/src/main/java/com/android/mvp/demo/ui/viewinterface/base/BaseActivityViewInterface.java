package com.android.mvp.demo.ui.viewinterface.base;

import android.content.Context;
import android.view.View;

import com.android.mvp.demo.presentor.base.BaseActivityPresentorI;


/**
 * Created by Administrator on 2017/11/24 0024.
 */

public interface BaseActivityViewInterface {

    public Context getViewContext();

    public void setActivityTitle(String title);

    public void setActivityActionBar(int layoutId);

    public void setActivityTopRightMenuText(String text);

    public void setActivityTopRightMenuBG(int drawbleId);

    public BaseActivityPresentorI getViewPresentor();

    public View getRightMenuView();

    public void setRighMenuVisible(int visible);
}
