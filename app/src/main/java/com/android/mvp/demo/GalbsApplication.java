package com.android.mvp.demo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import android.util.Log;


import com.android.mvp.demo.data.db.bean.MyObjectBox;
import com.android.mvp.demo.data.sharepreference.CommonsDataUtils;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;


/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class GalbsApplication extends Application {

    private static SharedPreferences commSpf;
    private static final String COMM_SPF_FILE_NAME = "com.android.tcd.galbs.GALBS_SPF_FILE_NAME";
    private static GalbsApplication galbsApplication;
    private CommonsDataUtils cdus;
    public static SharedPreferences getAppPrefrences() {
        return commSpf;
    }
    private String OrderID = ""; // 保存最新支付订单号
    public static GalbsApplication getGalbsApplication(){
        return galbsApplication;
    }
    private Context context;

    //objectBox数据库
    private BoxStore boxStore;

    private List<Activity> activityHistory = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        galbsApplication = this;
        commSpf = context.getSharedPreferences(COMM_SPF_FILE_NAME,
                Context.MODE_PRIVATE);
        cdus = CommonsDataUtils.getInstances();

        boxStore = MyObjectBox.builder().androidContext(GalbsApplication.this).build();
//        if (BuildConfig.DEBUG) {
//            new AndroidObjectBrowser(boxStore).start(this);
//        }
    }



    public BoxStore getBoxStore() {
        return boxStore;
    }


    public void addActivity(Activity activity) {
        activityHistory.add(activity);
        Log.d("get_app_test", "addActivity = " + activityHistory.size());
    }

    public void removeActivity(Activity activity) {
        activityHistory.remove(activity);
        Log.d("get_app_test", "removeActivity = " + activityHistory.size());
    }

    public void clearActivityStack() {
        if (null != activityHistory && activityHistory.size() > 0) {
            for (Activity activity : activityHistory) {
                activity.finish();
            }
        }
    }

}
