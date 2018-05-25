package com.android.mvp.demo.ui.viewinterface;

import android.view.animation.Animation;

import com.android.mvp.demo.ui.viewinterface.base.BaseActivityViewInterface;


/**
 * Created by Administrator on 2017/11/24 0024.
 */

public interface StartUpActivityViewInterface extends BaseActivityViewInterface {
    public void finishActivity();
    public void startViewAnimation(Animation animation);
    public void stopViewAnimation();
}
