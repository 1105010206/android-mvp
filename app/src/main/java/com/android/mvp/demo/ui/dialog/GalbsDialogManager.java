package com.android.mvp.demo.ui.dialog;

import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.mvp.demo.R;


/**
 * 作者: 刘康
 * 时间: 2017/12/13 0013 9:13
 */

public class GalbsDialogManager {

    //默认progressbar超时时间
    private final static int DEFAULT_PROGRESSBAR_OUT_TIME = 5 * 1000;
    //关闭progressdialog
    private final static int OUT_TIME_DISMISS_PROGRESSBAR_DIALOG = 1;

    private static GalbsDialogPlus currentDialog = null;

    private static ProgressDialogOutTime outTimeCallBack = null;

    static android.os.Handler mHandler = new android.os.Handler( ){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case OUT_TIME_DISMISS_PROGRESSBAR_DIALOG:
                    if (null != currentDialog && currentDialog.isShowing()) {
                        currentDialog.dismiss();
                        currentDialog = null;
                        if (null != outTimeCallBack) {
                            outTimeCallBack.outTime("");
                        }
                    }
                    break;
            }
        }
    };

    public static void dismissCurrentDialog() {
        if (null != currentDialog && currentDialog.isShowing()) {
            currentDialog.dismiss();
            currentDialog = null;
            outTimeCallBack = null;
            mHandler.removeMessages(OUT_TIME_DISMISS_PROGRESSBAR_DIALOG);
        }
    }

    public static void showProgressDialog(GalbsDialogBuilder builder) {
        if (null == builder) {
            throw new NullPointerException("GalbsDialogBuilder may not be null");
        }
        //先关闭当前的对话框
        dismissCurrentDialog();
        Holder holder = new ViewHolder(R.layout.dialog_base_progress_layout);
        final GalbsDialogPlus dialog = GalbsDialogPlus
                .newDialog(builder.getContext())
                .setContentHolder(holder)
                .setGravity(builder.getGravity())
                .setOnDismissListener(builder.getOnDismissListener())
                .setExpanded(builder.getExpanded())
                .setCancelable(builder.getCancelable())
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOverlayBackgroundResource(builder.getOverlayBackgroundResource())
                .create();
        dialog.show();
        View progressView = ((ViewHolder)holder).getContentView();
        ProgressBar progressBar = progressView.findViewById(R.id.dialog_progress);
        TextView textView = progressView.findViewById(R.id.progress_text);
        textView.setTextColor(builder.getContext().getResources().getColor(R.color.white));
        textView.setText(builder.getProgressText());

        currentDialog = dialog;
        outTimeCallBack = builder.getOutTimeCallback();
        //超时关闭进度框
        mHandler.sendEmptyMessageDelayed(OUT_TIME_DISMISS_PROGRESSBAR_DIALOG, builder.getOutTime());

    }


    public static void showSureCancelDialog(final GalbsDialogBuilder builder) {
        if (null == builder) {
            throw new NullPointerException("GalbsDialogBuilder may not be null");
        }
        //先关闭当前的对话框
        dismissCurrentDialog();
        Holder holder = new ViewHolder(R.layout.dialog_base_alert_layout);
        final GalbsDialogPlus dialog = GalbsDialogPlus
                .newDialog(builder.getContext())
                .setContentHolder(holder)
                .setGravity(builder.getGravity())
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(GalbsDialogPlus dialog, View view) {
                        if (R.id.dialog_cancel_button == view.getId()) {
                            builder.getCancelButtonClickListener().onClick(view);
                        } else if (R.id.dialog_sure_button == view.getId()) {
                            builder.getSureButtonClickListener().onClick(view);
                        }
                    }
                })
                .setOnDismissListener(builder.getOnDismissListener())
                .setExpanded(builder.getExpanded())
                .setCancelable(builder.getCancelable())
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOverlayBackgroundResource(builder.getOverlayBackgroundResource())
                .create();
        dialog.show();
        currentDialog = dialog;
        View dialogView = ((ViewHolder)holder).getContentView();
        TextView titleView = dialogView.findViewById(R.id.dialog_title);
        TextView msgView = dialogView.findViewById(R.id.dialog_msg);
        Button button = dialogView.findViewById(R.id.only_button);
        button.setVisibility(View.GONE);
        titleView.setText(builder.getBaseDialogTitle());
        msgView.setText(builder.getBaseDialogMsg());

    }

    public static void showOnlyButtonDialog(final GalbsDialogBuilder builder) {
        if (null == builder) {
            throw new NullPointerException("GalbsDialogBuilder may not be null");
        }
        //先关闭当前的对话框
        dismissCurrentDialog();
        Holder holder = new ViewHolder(R.layout.dialog_base_alert_layout);
        final GalbsDialogPlus dialog = GalbsDialogPlus
                .newDialog(builder.getContext())
                .setContentHolder(holder)
                .setGravity(builder.getGravity())
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(GalbsDialogPlus dialog, View view) {
                        if (R.id.only_button == view.getId()) {
                            builder.getOnlyButtonClickListener().onClick(view);
                        }
                    }
                })
                .setOnDismissListener(builder.getOnDismissListener())
                .setExpanded(builder.getExpanded())
                .setCancelable(builder.getCancelable())
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOverlayBackgroundResource(builder.getOverlayBackgroundResource())
                .create();
        dialog.show();
        currentDialog = dialog;
        View dialogView = ((ViewHolder)holder).getContentView();
        TextView titleView = dialogView.findViewById(R.id.dialog_title);
        TextView msgView = dialogView.findViewById(R.id.dialog_msg);
        LinearLayout linearLayout = dialogView.findViewById(R.id.sure_cancel_layout);
        linearLayout.setVisibility(View.GONE);
        titleView.setText(builder.getBaseDialogTitle());
        msgView.setText(builder.getBaseDialogMsg());

    }

    public static View showCustomDialog(final GalbsDialogBuilder builder) {
        if (null == builder) {
            throw new NullPointerException("GalbsDialogBuilder may not be null");
        }
        //先关闭当前的对话框
        dismissCurrentDialog();
        Holder holder = new ViewHolder(builder.getDialogLayoutId());
        final GalbsDialogPlus dialog = GalbsDialogPlus
                .newDialog(builder.getContext())
                .setContentHolder(holder)
                .setGravity(builder.getGravity())
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(GalbsDialogPlus dialog, View view) {
                        builder.getCustomDialogClickListener().onClick(view);
                    }
                })
                .setOnDismissListener(builder.getOnDismissListener())
                .setExpanded(builder.getExpanded())
                .setCancelable(builder.getCancelable())
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOverlayBackgroundResource(builder.getOverlayBackgroundResource())
                .create();
        dialog.show();
        currentDialog = dialog;
        View dialogView = ((ViewHolder)holder).getContentView();
        return dialogView;
    }
}
