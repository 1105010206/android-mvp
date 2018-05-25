package com.android.mvp.demo.ui.viewinterface;


import com.android.mvp.demo.ui.dialog.GalbsDialogBuilder;
import com.android.mvp.demo.ui.viewinterface.base.BaseActivityViewInterface;

/**
 * Created by Administrator on 2017/11/24 0024.
 */

public interface LogInActivityViewInterface extends BaseActivityViewInterface {
    public void onLogInButtonClick();

    public String getUserName();

    public String getPassWord();

    public void setUserName(String number);

    public void setPassWord(String passWord);

    public void startLogInProgressDialog(GalbsDialogBuilder builder);

    public void stopLogInProgressDialog();

    public void showPerrmissionDialog(GalbsDialogBuilder builder);

    public void showActivateDialog(GalbsDialogBuilder builder);

    public void dismissPermissionDialog();

    public void finishView();

    public void setHelpTips(String tips);
}
