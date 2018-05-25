package com.android.mvp.demo.presentor.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.android.mvp.demo.GalbsApplication;
import com.android.mvp.demo.R;
import com.android.mvp.demo.data.eventmsg.LogInMessage;
import com.android.mvp.demo.data.sharepreference.CommonsDataUtils;
import com.android.mvp.demo.presentor.StartUpActivityPresentorI;
import com.android.mvp.demo.presentor.base.BaseActivityPresentor;
import com.android.mvp.demo.ui.activity.LogInActivity;
import com.android.mvp.demo.ui.viewinterface.StartUpActivityViewInterface;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Administrator on 2017/11/15 0015.
 */

public class StartUpActivityPresentor<V extends StartUpActivityViewInterface>  extends BaseActivityPresentor implements
        StartUpActivityPresentorI {
    Context context;
    V viewImp;
    Animation viewAnimation;
    Animation.AnimationListener animationListener;

    CommonsDataUtils cdus = CommonsDataUtils.getInstances();

    public final int CLOSE_THIS_ACTIVITY = 1;
    public final int DELAY_START_UP_TIME = 2000;

    public StartUpActivityPresentor(V viewImp){
        this.viewImp = viewImp;
        this.context = viewImp.getViewContext();
    }

    @Override
    public boolean onViewKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            return true;
        }
        return false;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        EventBus.getDefault().postSticky(new LogInMessage("我发送了一个event事件"));
        viewAnimation = AnimationUtils.loadAnimation(context, R.anim.dialog_start_up_anim);
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                needGuide();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        viewAnimation.setAnimationListener(animationListener);
        viewImp.startViewAnimation(viewAnimation);
    }

    @Override
    public void startUpPushService() {

    }

    /*判断是否是第一次使用该APP，如果是，则跳入引导页，否则直接进入登录页面*/
    private void needGuide() {
//        if (cdus.isLogin()) {
//            //上一次已经登录成功，则在启动界面就下拉软件配置数据，免得再去主页面花时间下拉数据
//            GalbsApplication.getGalbsApplication().getGalbsAllData().updateAllData();
//            startUpPushService();
//        }
        mHandler.sendEmptyMessageDelayed(CLOSE_THIS_ACTIVITY, DELAY_START_UP_TIME);
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what) {
                case CLOSE_THIS_ACTIVITY:
                    Intent intent ;
//                    if (!cdus.isGuide()) {
//                        intent = new Intent(context, GuidePageActivity.class);
//                    }else if (cdus.isLogin()){//上次登录成功了，则直接进入主页，免得每次都需要重复输入用户名进行登录
//                        intent = new Intent(context, MainActivity.class);
//                    }else{
//
//                    }
                    intent = new Intent(context, LogInActivity.class);
                    context.startActivity(intent);
                    viewImp.finishActivity();
                    break;
            }
        }
    };
}
