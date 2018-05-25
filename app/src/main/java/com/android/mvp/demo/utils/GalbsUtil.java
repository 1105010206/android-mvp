package com.android.mvp.demo.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;


import com.android.mvp.demo.GalbsApplication;
import com.android.mvp.demo.R;
import com.android.mvp.demo.data.sharepreference.CommonsDataUtils;
import com.android.mvp.demo.ui.activity.LogInActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 */

public class GalbsUtil {

    /*standard模式*/
    public static void startActiviyByStandard(Context context, Intent intent) {
        context.startActivity(intent);
    }

    /*singleTop模式*/
    public static void startActiviyBySingleTop(Context context, Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    /*singleTask模式*/
    public static void startActiviyBySingleTask(Context context, Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /*singleInstance模式*/
    public static void startActiviyBySingleInstance(Context context, Intent intent) {
        // context.startActivity(intent);
    }

    /*退出APP登录,返回到登录界面*/
    public static void exitLogIn(Context context) {
        //杀掉所有历史任务
        GalbsApplication.getGalbsApplication().clearActivityStack();

        CommonsDataUtils cdus = CommonsDataUtils.getInstances();
        Intent startIntent = new Intent(context, LogInActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(startIntent);
    }

    /*获取权限名称*/
    public static String getPermission(Context context , String permission) {
        String name = null;
        if (android.Manifest.permission.CAMERA.equals(permission)) {
            name = context.getResources().getString(R.string.camera_permission);
        }else if (android.Manifest.permission.READ_EXTERNAL_STORAGE.equals(permission)){
            name = context.getResources().getString(R.string.read_external_storage_permission);
        }else if (android.Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission)){
            name = context.getResources().getString(R.string.write_external_storage_permission);
        }else if (android.Manifest.permission.RECORD_AUDIO.equals(permission)){
            name = context.getResources().getString(R.string.record_audio_permission);
        }else if (android.Manifest.permission.READ_PHONE_STATE.equals(permission)){
            name = context.getResources().getString(R.string.read_phone_state_permission);
        }else if (android.Manifest.permission.INTERNET.equals(permission)){
            name = context.getResources().getString(R.string.internet_permission);
        }else if (android.Manifest.permission.SEND_SMS.equals(permission)){
            name = context.getResources().getString(R.string.send_sms_permission);
        }else if (android.Manifest.permission.READ_SMS.equals(permission)){
            name = context.getResources().getString(R.string.read_sms_permission);
        }
        return name;
    }

    /*跳转到系统设置界面设置应用权限*/
    public static void goToSettingPermission(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }

}
