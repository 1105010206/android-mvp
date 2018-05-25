package com.android.mvp.demo.presentor.impl;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;

import com.android.mvp.demo.R;
import com.android.mvp.demo.data.db.impl.GalbsDBManager;
import com.android.mvp.demo.data.eventmsg.LogInMessage;
import com.android.mvp.demo.data.protocol.request.base.GalbsMsg;
import com.android.mvp.demo.data.protocol.response.LogInResponse;
import com.android.mvp.demo.data.sharepreference.CommonsDataUtils;
import com.android.mvp.demo.presentor.LogInActivityPresentorI;
import com.android.mvp.demo.presentor.base.BaseActivityPresentor;
import com.android.mvp.demo.ui.dialog.GalbsDialogBuilder;
import com.android.mvp.demo.ui.dialog.GalbsDialogManager;
import com.android.mvp.demo.ui.dialog.ProgressDialogOutTime;
import com.android.mvp.demo.ui.viewinterface.LogInActivityViewInterface;
import com.android.mvp.demo.utils.FormatUtils;
import com.android.mvp.demo.utils.GalbsUtil;
import com.android.mvp.demo.utils.RequestContentUtils;
import com.android.mvp.demo.utils.ToastUtil;
import com.android.mvp.demo.utils.network.NetManager;
import com.android.mvp.rxpermissions.Permission;
import com.android.mvp.rxpermissions.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/28 0028.
 */

public class LogInActivityPresentor<V extends LogInActivityViewInterface>  extends BaseActivityPresentor implements LogInActivityPresentorI {

    Context context;
    V viewImpl;
    String userNumber;
    String passWord;
    NetManager netManager;
    LogInResponse logInResponse;
    GalbsDBManager dbManager;
    CommonsDataUtils cdus = CommonsDataUtils.getInstances();
    private static final int LOG_IN_OUT_TIME = 5 * 1000 ;

    RxPermissions rxPermissions ;

    public static final int SWITCH_HELP_TIPS = 3 * 1000;

    boolean getPermission = true;
    int permissionCount = 0;

    //UserManager userManager;

    public LogInActivityPresentor( V viewImpl) {
        this.viewImpl = viewImpl;
        this.context = viewImpl.getViewContext();
        netManager = new NetManager(context);
        dbManager = new GalbsDBManager();
        rxPermissions = new RxPermissions((Activity) context);
        rxPermissions.setLogging(true);
        //userManager = UserManager.getInstance();
    }

    private void getPermissonSuccessful(){
        userNumber = viewImpl.getUserName();
        passWord = viewImpl.getPassWord();
        if (!checkLegality(userNumber, passWord)) {
            return;
        }
        accessServer(RequestContentUtils.getAppLogInContent(context,userNumber,passWord) ,false);
    }

    @Override
    public void onLogIn() {
        Observable.just(null)
                .compose(rxPermissions.ensureEach(Manifest.permission.READ_PHONE_STATE))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Permission>() {
                               @Override
                               public void call(Permission permission) {
                                   if (permission.granted) {
                                       getPermissonSuccessful();
                                   } else if (permission.shouldShowRequestPermissionRationale) {
                                       // Denied permission without ask never again
                                       String baseStr = context.getResources().getString(R.string.need_permission);
                                       String needPermissions = String.format(baseStr, GalbsUtil.getPermission(context,permission.name));
                                       ToastUtil.showLongToast(needPermissions);
                                   } else {
                                       String baseStr = context.getResources().getString(R.string.forbidden_the_permission);
                                       String forbiddenPermissions = String.format(baseStr, GalbsUtil.getPermission(context,permission.name));

                                       GalbsDialogBuilder builder = new
                                               GalbsDialogBuilder(context)
                                               .setBaseDialogTitle(context.getResources().getString(R.string.permission_check))
                                               .setSureButtonClickListener(permissionSureBt)
                                               .setBaseDialogMsg(forbiddenPermissions);
                                       viewImpl.showPerrmissionDialog(builder);
                                   }


                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable t) {
                                ToastUtil.showLongToast(R.string.request_permission_exception);
                            }
                        },
                        new Action0() {
                            @Override
                            public void call() {

                            }
                        });
    }

    private void accessServer(GalbsMsg msg , final boolean isCheck) {
        mCompositeSubscription.add(netManager.appLogIn(msg)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        GalbsDialogBuilder builder = new GalbsDialogBuilder(context)
                                .setProgressText(context.getResources().getString(R.string.dialog_log_in_message))
                                .setCancelable(false)
                                .setOutTime(LOG_IN_OUT_TIME)
                                .setOutTimeCallback(progressDialogOutTime);
                        viewImpl.startLogInProgressDialog(builder);
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LogInResponse>() {
                    @Override
                    public void onCompleted() {
                        viewImpl.stopLogInProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LogInResponse logInResponse) {
                        parseLogInResponse(logInResponse , isCheck);
                    }
                }));
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onViewDestroy() {
        super.onViewDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageRecieve(LogInMessage msg) {
        ToastUtil.showShortToast(msg.getMessage());
        EventBus.getDefault().removeStickyEvent(LogInMessage.class);
    }

    private boolean checkLegality(String numberStr, String passWordStr) {
        boolean isLegality = true;
        if (null == numberStr || TextUtils.isEmpty(numberStr)) {
            ToastUtil.showShortToast(R.string.input_account_name_not_empty);
            return false;
        }
        if (null == passWordStr || TextUtils.isEmpty(passWordStr)) {
            ToastUtil.showShortToast(R.string.input_password_not_empty);
            return false;
        }
        if (!FormatUtils.isNumeric(numberStr)) {
            ToastUtil.showShortToast(R.string.input_account_format_error);
            return false;
        }
        return isLegality;
    }

    private void parseLogInResponse(final LogInResponse logInResponse, boolean isCheck) {
        if (null == logInResponse) {
            return;
        }

        int state = logInResponse.getState();

        if (state == 1) {
            goToMainPage();
            viewImpl.finishView();
        } else {

        }
    }

    private void goToMainWithAppNotActivate(final LogInResponse logInResponse ,final String passWord){
        mCompositeSubscription.add(Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("");
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                goToMainPage();
            }
        }));

    }

    private void goToMainWithDeviceNotActivate(){
        mCompositeSubscription.add(Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String username = viewImpl.getUserName();
                String password = viewImpl.getPassWord();
                subscriber.onNext("");
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                goToMainPage();
            }
        }));


    }

    private void goToMainPage(){
//        Intent intent = new Intent(context, MainActivity.class);
//        context.startActivity(intent);
    }

    ProgressDialogOutTime progressDialogOutTime = new ProgressDialogOutTime() {
        @Override
        public void outTime(String outTimeInfo) {
            ToastUtil.showLongToast(R.string.log_in_out_time);
        }
    };

    View.OnClickListener permissionSureBt = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GalbsDialogManager.dismissCurrentDialog();
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
            intent.setData(uri);
            context.startActivity(intent);
        }
    };



    @Override
    public void startUpPushService() {

    }

    @Override
    public void onActivityForResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onScanClick() {
        permissionCount = 0;
        getPermission = true;
        Observable.just(null)
                .compose(rxPermissions.ensureEach(new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA}))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Permission>() {
                               @Override
                               public void call(Permission permission) {
                                   ++permissionCount;
                                   if (permission.granted) {
                                       //getPermissonSuccessful();
                                       if (3 == permissionCount && getPermission) {
                                           ToastUtil.showLongToast("获取到了权限");
                                       }
                                   } else if (permission.shouldShowRequestPermissionRationale) {
                                       // Denied permission without ask never again
                                       getPermission = false;
                                       String baseStr = context.getResources().getString(R.string.need_permission);
                                       String needPermissions = String.format(baseStr, GalbsUtil.getPermission(context,permission.name));
                                       ToastUtil.showLongToast(needPermissions);
                                   } else {
                                       getPermission = false;
                                       String baseStr = context.getResources().getString(R.string.forbidden_the_permission);
                                       String forbiddenPermissions = String.format(baseStr, GalbsUtil.getPermission(context,permission.name));
                                       GalbsDialogBuilder builder = new
                                               GalbsDialogBuilder(context)
                                               .setBaseDialogTitle(context.getResources().getString(R.string.permission_check))
                                               .setSureButtonClickListener(permissionSureBt)
                                               .setBaseDialogMsg(forbiddenPermissions);
                                       viewImpl.showPerrmissionDialog(builder);
                                   }
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable t) {
                                ToastUtil.showLongToast(R.string.request_permission_exception);
                            }
                        },
                        new Action0() {
                            @Override
                            public void call() {

                            }
                        });
    }

    @Override
    public void onHelpClick() {
        String reminderString = null;
        reminderString = context.getResources().getString(R.string.dialog_tips);
        viewImpl.setHelpTips(reminderString);

        mCompositeSubscription.add(Observable.interval(SWITCH_HELP_TIPS, TimeUnit.MILLISECONDS)
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        viewImpl.setHelpTips(context.getResources().getString(R.string.login_tips));
                    }
                }));
        //MyDialog.dialogTipsChoice(this,reminderString);
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                logInTips.setText(getString(R.string.login_tips));
//                MyDialog.dismiss();
//            }
//        }, 3000);
    }

    @Override
    public void onForgetPasswordClick() {
//        Intent intent = new Intent(context, ResetPasswordActivity.class);
//        intent.putExtra(Constant.RESET_PASSWORD_MODE_STRING, Constant.ResetPasswordMode.FORGET_PASSORD_MODE.mode);
//        context.startActivity(intent);
    }

}
