package com.android.mvp.demo.presentor;

import android.content.Intent;

import com.android.mvp.demo.presentor.base.BaseActivityPresentorI;
import com.android.mvp.demo.ui.viewinterface.LogInActivityViewInterface;


/**
*
*@author 刘康
*@time 2017/11/30 0030 17:59
*/
public interface LogInActivityPresentorI<V extends LogInActivityViewInterface> extends BaseActivityPresentorI {
    public void onLogIn();
    public void startUpPushService();
    public void onActivityForResult(int requestCode, int resultCode, Intent data);
    public void onScanClick();
    public void onHelpClick();
    public void onForgetPasswordClick();
}
