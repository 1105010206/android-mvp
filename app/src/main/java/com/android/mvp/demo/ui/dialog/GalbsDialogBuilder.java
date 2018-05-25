package com.android.mvp.demo.ui.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.android.mvp.demo.R;

/**
 * 作者: 刘康
 * 时间: 2017/12/13 0013 17:49
 */

public class GalbsDialogBuilder {

    Context context;
    String progressText = "";
    boolean isCancelable = true;
    int gravity = Gravity.CENTER;
    boolean isExpanded = false;
    OnDismissListener onDismissListener;
    int outTime = 5 * 1000;
    int overlayBackgroundResource = R.color.dialog_overlay_color;
    ProgressDialogOutTime outTimeCallback;

    private String baseDialogTitle = "";

    private String baseDialogMsg = "";

    private int customDialogLayoudId = R.layout.dialog_base_alert_layout;

    private View.OnClickListener sureButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GalbsDialogManager.dismissCurrentDialog();
        }
    };

    private View.OnClickListener cancelButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GalbsDialogManager.dismissCurrentDialog();
        }
    };

    private View.OnClickListener customDialogOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GalbsDialogManager.dismissCurrentDialog();
        }
    };

    private View.OnClickListener onlyButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GalbsDialogManager.dismissCurrentDialog();
        }
    };

    public GalbsDialogBuilder(Context context) {
        if (null == context) {
            throw new NullPointerException("Context may not be null");
        }
        this.context = context;
    }

    public GalbsDialogBuilder setProgressText(String text) {
        if (null == text) {
            text = "";
        }
        this.progressText = text;
        return this;
    }

    public GalbsDialogBuilder setCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
        return this;
    }

    public GalbsDialogBuilder setGravity(int gravity) {
        if (gravity != Gravity.CENTER ||
                gravity != Gravity.BOTTOM ||
                gravity != Gravity.TOP) {
            gravity = Gravity.CENTER;
        }
        this.gravity = gravity;
        return this;
    }

    public GalbsDialogBuilder setExpanded(boolean isExpanded) {
        this.isExpanded = isExpanded;
        return this;
    }

    public GalbsDialogBuilder setOutTime(int outTime) {
        this.outTime = outTime;
        return this;
    }

    public GalbsDialogBuilder setOverlayBackgroundResource(int overlayBackgroundResource) {
        this.overlayBackgroundResource = overlayBackgroundResource;
        return this;
    }

    public GalbsDialogBuilder setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public GalbsDialogBuilder setOutTimeCallback(ProgressDialogOutTime outTimeCallback) {
        this.outTimeCallback = outTimeCallback;
        return this;
    }

    public GalbsDialogBuilder setSureButtonClickListener(View.OnClickListener listener) {
        this.sureButtonClickListener = listener;
        return this;
    }

    public GalbsDialogBuilder setOnlyButtonClickListener(View.OnClickListener listener) {
        this.onlyButtonClickListener = listener;
        return this;
    }

    public GalbsDialogBuilder setCancelButtonClickListener(View.OnClickListener listener) {
        this.cancelButtonClickListener = listener;
        return this;
    }


    public GalbsDialogBuilder setBaseDialogTitle(String baseDialogTitle) {
        this.baseDialogTitle = baseDialogTitle;
        return this;
    }
    public GalbsDialogBuilder setBaseDialogMsg(String baseDialogMsg) {
        this.baseDialogMsg = baseDialogMsg;
        return this;
    }

    public GalbsDialogBuilder setDialogLayoutId(int layoutId) {
        this.customDialogLayoudId = layoutId;
        return this;
    }

    public GalbsDialogBuilder setCustomDialogClickListener(View.OnClickListener listener) {
        this.customDialogOnClickListener = listener;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public String getProgressText() {
        return progressText;
    }

    public boolean getCancelable() {
        return isCancelable;
    }

    public int getGravity() {
        return gravity;
    }

    public boolean getExpanded() {
        return isExpanded;
    }

    public int getOutTime() {
        return outTime;
    }

    public int getOverlayBackgroundResource(){
        return overlayBackgroundResource;
    }

    public OnDismissListener getOnDismissListener() {
        return onDismissListener;
    }

    public ProgressDialogOutTime getOutTimeCallback() {
        return outTimeCallback;
    }

    public View.OnClickListener getSureButtonClickListener() {
        return sureButtonClickListener;
    }

    public View.OnClickListener getOnlyButtonClickListener() {
        return onlyButtonClickListener;
    }

    public View.OnClickListener getCancelButtonClickListener() {
        return cancelButtonClickListener;
    }

    public String getBaseDialogTitle() {
        return baseDialogTitle;
    }

    public String getBaseDialogMsg() {
        return baseDialogMsg;
    }

    public int getDialogLayoutId() {
        return customDialogLayoudId ;
    }

    public View.OnClickListener getCustomDialogClickListener() {
        return customDialogOnClickListener;
    }
}
