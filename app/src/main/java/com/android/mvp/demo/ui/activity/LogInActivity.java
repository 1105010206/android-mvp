package com.android.mvp.demo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mvp.demo.R;
import com.android.mvp.demo.presentor.LogInActivityPresentorI;
import com.android.mvp.demo.presentor.base.BaseActivityPresentorI;
import com.android.mvp.demo.presentor.impl.LogInActivityPresentor;
import com.android.mvp.demo.ui.base.BaseActivity;
import com.android.mvp.demo.ui.dialog.GalbsDialogBuilder;
import com.android.mvp.demo.ui.dialog.GalbsDialogManager;
import com.android.mvp.demo.ui.viewinterface.LogInActivityViewInterface;
import com.android.mvp.demo.utils.EnumUtil;


/**
*
*@time 2017/11/28 0028 16:24
*/
public class LogInActivity extends BaseActivity implements LogInActivityViewInterface,View.OnClickListener{

    LogInActivityPresentorI viewPresentor;
    EditText userNameET;
    EditText passWordET;
    Button logInButton;
    ImageView scanQrCode;
    ImageView helpIV;
    TextView helpTipTV;
    TextView forgetPasswordTV;
    @Override
    protected void onViewCreat(Bundle bundle) {
        setContentView(R.layout.activity_login_layout);
        initView();
    }

    @Override
    protected BaseActivityPresentorI initViewPresentor() {
        return viewPresentor = new LogInActivityPresentor<LogInActivityViewInterface>(this);
    }

    private void initView(){
        userNameET = (EditText)findViewById(R.id.ET_lg_number);
        passWordET = (EditText)findViewById(R.id.ET_lg_pwd);
        scanQrCode = findViewById(R.id.ET_scan_number);
        helpIV = findViewById(R.id.ET_help);
        logInButton = (Button)findViewById(R.id.BT_lg_login);
        helpTipTV = findViewById(R.id.tips_tv);
        forgetPasswordTV = findViewById(R.id.how_get_pwd_text);

        logInButton.setOnClickListener(this);
        scanQrCode.setOnClickListener(this);
        helpIV.setOnClickListener(this);
        forgetPasswordTV.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        viewPresentor.onViewDestroy();
    }

    @Override
    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);
    }

    @Override
    protected EnumUtil.ActivityType getActivityType() {
        return EnumUtil.ActivityType.NO_TITLE;
    }

    @Override
    public void onLogInButtonClick() {

    }

    @Override
    public String getUserName() {
        return userNameET.getText().toString();
    }

    @Override
    public String getPassWord() {
        return passWordET.getText().toString();
    }

    @Override
    public void setUserName(String number) {
        if (TextUtils.isEmpty(number)) {
            number = "";
        }
        userNameET.setText(number);
        userNameET.setSelection(number.trim().length());
    }

    @Override
    public void setPassWord(String passWord) {
        if (TextUtils.isEmpty(passWord)) {
            passWord = "";
        }
        passWordET.setText(passWord);
        passWordET.setSelection(passWord.trim().length());



    }

    @Override
    public void startLogInProgressDialog(GalbsDialogBuilder builder) {
        GalbsDialogManager.showProgressDialog(builder);
    }

    @Override
    public void stopLogInProgressDialog() {
        GalbsDialogManager.dismissCurrentDialog();
    }

    @Override
    public void showPerrmissionDialog(GalbsDialogBuilder builder) {
        GalbsDialogManager.showSureCancelDialog(builder);
    }

    @Override
    public void showActivateDialog(GalbsDialogBuilder builder) {
        GalbsDialogManager.showSureCancelDialog(builder);
    }

    @Override
    public void dismissPermissionDialog() {
        GalbsDialogManager.dismissCurrentDialog();
    }

    @Override
    public void finishView() {
        this.finish();
    }

    @Override
    public void setHelpTips(String tips) {
        if (TextUtils.isEmpty(tips)) {
            helpTipTV.setText("");
        }else{
            helpTipTV.setText(tips);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BT_lg_login:
                viewPresentor.onLogIn();
                break;
            case R.id.ET_scan_number:
                viewPresentor.onScanClick();
                break;
            case R.id.ET_help:
                viewPresentor.onHelpClick();
                break;
            case R.id.how_get_pwd_text:
                viewPresentor.onForgetPasswordClick();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        viewPresentor.onActivityForResult(requestCode, resultCode, data);
    }
}
