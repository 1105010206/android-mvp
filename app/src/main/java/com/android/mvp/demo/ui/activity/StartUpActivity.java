package com.android.mvp.demo.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.android.mvp.demo.R;
import com.android.mvp.demo.presentor.StartUpActivityPresentorI;
import com.android.mvp.demo.presentor.base.BaseActivityPresentorI;
import com.android.mvp.demo.presentor.impl.StartUpActivityPresentor;
import com.android.mvp.demo.ui.base.BaseActivity;
import com.android.mvp.demo.ui.viewinterface.StartUpActivityViewInterface;
import com.android.mvp.demo.utils.EnumUtil;

/**
*
*@author 刘康
*@time 2017/11/28 0028 16:24
*/

public class StartUpActivity extends BaseActivity implements StartUpActivityViewInterface {

    StartUpActivityPresentorI viewPresentor;
    ImageView logView;

    @Override
    protected void onViewCreat(Bundle bundle) {
        setContentView(R.layout.activity_start_up_layout);
        initView();
    }

    @Override
    protected BaseActivityPresentorI initViewPresentor() {
        return viewPresentor = new StartUpActivityPresentor<>(this);
    }

    private void initView(){
        logView = (ImageView) findViewById(R.id.start_up);
    }

    @Override
    protected EnumUtil.ActivityType getActivityType() {
        return EnumUtil.ActivityType.NO_TITLE;
    }


    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void startViewAnimation(Animation animation) {
        logView.startAnimation(animation);
    }

    @Override
    public void stopViewAnimation() {
        logView.clearAnimation();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return viewPresentor.onViewKeyDown(keyCode, event);
    }
}
