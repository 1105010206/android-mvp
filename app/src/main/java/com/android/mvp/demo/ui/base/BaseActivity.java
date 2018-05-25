package com.android.mvp.demo.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mvp.demo.GalbsApplication;
import com.android.mvp.demo.R;
import com.android.mvp.demo.data.sharepreference.CommonsDataUtils;
import com.android.mvp.demo.presentor.base.BaseActivityPresentorI;
import com.android.mvp.demo.ui.viewinterface.base.BaseActivityViewInterface;
import com.android.mvp.demo.utils.EnumUtil;


/**
 * Created by Administrator on 2017/11/15 0015.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseActivityViewInterface {

    private ActionBar actionBar;
    private EnumUtil.ActivityType activityType;
    protected CommonsDataUtils cdus = CommonsDataUtils.getInstances();
    private ImageView comeBack;
    private TextView title;
    protected TextView rightMenu;
    protected Context context;
    private BaseActivityPresentorI presentor;

    @Override
    public void onCreate(Bundle onSaveInstance) {
        super.onCreate(onSaveInstance);
        activityType = getActivityType();
        context = this;
        GalbsApplication.getGalbsApplication().addActivity(this);
        initActivityTheme();
        initActionBar();
        presentor = initViewPresentor();
        onViewCreat(onSaveInstance);
        if (null != presentor) {
            presentor.onViewCreated();
        }

    }

    protected abstract void onViewCreat(Bundle bundle);
    protected abstract BaseActivityPresentorI initViewPresentor();
    protected abstract EnumUtil.ActivityType getActivityType();

    protected void initActivityTheme() {

    }
    private void initActionBar() {
        actionBar = getSupportActionBar();
        if (null == activityType) {
            activityType = EnumUtil.ActivityType.NO_TITLE;
        }
        if (activityType.equals(EnumUtil.ActivityType.NO_TITLE)) {
            actionBar.hide();
        } else if (activityType.equals(EnumUtil.ActivityType.HAVE_TITLE)) {
            actionBar.show();
            setActionBarParas(null);
        }
    }

    public void setActionBar(int layoutId){
        if (null == actionBar) {
            return;
        }
        setActionBar(findViewById(layoutId));
    }

    private void setActionBarParas(View viewActionBar){
        View view;
        if (null == viewActionBar) {
            view = LayoutInflater.from(this).inflate(R.layout.default_activity_top_action_bar_layout,null);
            comeBack = (ImageView) view.findViewById(R.id.headBar_back);
            title = (TextView) view.findViewById(R.id.headBar_title);
            rightMenu = (TextView) view.findViewById(R.id.headBar_operation);

            comeBack.setOnClickListener(defaultOnclickListener);
            rightMenu.setOnClickListener(defaultOnclickListener);
        }else{
            view = viewActionBar;
        }
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL;
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(view, layoutParams);
        Toolbar parent = (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0, 0);
    }

    public void setActionBar(View contentView) {
        setActionBarParas(contentView);
    }

    public void hideActionBar(boolean isHide) {
        if (isHide) {
            if (null != actionBar) {
                actionBar.hide();
        }
        }
    }

    View.OnClickListener defaultOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int viewId = view.getId();
            switch (viewId) {
                case R.id.headBar_back:
                    hookBeforeFinish();
                    finish();
                    break;
                case R.id.headBar_operation:
                    onRightMenuClick();
                    break;
                    default:

                        break;
            }
        }
    };

    /*结束当前activity之前需要完成的事情*/
    protected void hookBeforeFinish(){

    }

    protected void setActionBarTitle(String titleStr) {
        if (null != title) {
            if (null == titleStr) {
                title.setText("");
            }else{
                title.setText(titleStr);
            }
        }
    }

    /*右边菜单被点击*/
    protected void onRightMenuClick() {
        if (null != presentor) {
            presentor.onTopRightMenuClick();
        }
    }

    protected void setActionBarTitle(int resId) {
        setActionBarTitle(getResources().getString(resId));
    }

    @Override
    public Context getViewContext() {
        return context;
    }

    @Override
    public void setActivityTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            setActionBarTitle(title);
        }

    }

    @Override
    public void setActivityActionBar(int layoutId){
        if (0 != layoutId) {
            setActionBar(layoutId);
        }
    }

    protected void setRightMenuText(String text) {
        if (!TextUtils.isEmpty(text)&& null != rightMenu) {
            rightMenu.setText(text);
            rightMenu.setVisibility(View.VISIBLE);
        }else if (TextUtils.isEmpty(text)&& null != rightMenu){
            rightMenu.setText("");
            rightMenu.setVisibility(View.INVISIBLE);
        }
    }

    protected void setRightMenuBackgroud(int drawbleId) {
        if (0 !=drawbleId&& null != rightMenu) {
            rightMenu.setBackgroundResource(drawbleId);
            rightMenu.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setActivityTopRightMenuText(String text){
        setRightMenuText(text);
    }

    @Override
    public void setActivityTopRightMenuBG(int drawbleId){
        setRightMenuBackgroud(drawbleId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != presentor) {
            presentor.onViewDestroy();
        }

        GalbsApplication.getGalbsApplication().removeActivity(this);
    }

    @Override
    public BaseActivityPresentorI getViewPresentor(){
        return presentor;
    }

    @Override
    public View getRightMenuView(){
        return rightMenu;
    }

    @Override
    public void setRighMenuVisible(int visible){
        if (null != rightMenu) {
            rightMenu.setVisibility(visible);
        }
    }
}
