package com.android.mvp.demo.utils;

import android.widget.Toast;

import com.android.mvp.demo.GalbsApplication;


/**
 * 时间: 2017/11/29 0029 14:58
 */

public class ToastUtil {

    /** 之前显示的内容 */
    private static String oldMsg ;
    /** Toast对象 */
    private static Toast toast = null ;
    /** 第一次时间 */
    private static long oneTime = 0 ;
    /** 第二次时间 */
    private static long twoTime = 0 ;

    /**
     * 显示Toast
     *
     * @param message
     */
    public static void showShortToast(String message){
        if(toast == null){
            synchronized (ToastUtil.class) {
                if (null == toast){
                    toast = Toast.makeText(GalbsApplication.getGalbsApplication(), message, Toast.LENGTH_SHORT);
                }
            }
            oldMsg = message;
            toast.show() ;
            oneTime = System.currentTimeMillis() ;
        }else{
            twoTime = System.currentTimeMillis() ;
            if(message.equals(oldMsg)){
                if(twoTime - oneTime > toast.getDuration()){
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show() ;
                }
            }else{
                toast.setDuration(Toast.LENGTH_SHORT);
                oldMsg = message ;
                toast.setText(message) ;
                toast.show() ;
            }
        }
        oneTime = twoTime ;
    }

    public static void showShortToast(int messageId){
        try {
            showShortToast(GalbsApplication.getGalbsApplication().getResources().getString(messageId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showLongToast(int messageId){
        try {
            showLongToast(GalbsApplication.getGalbsApplication().getResources().getString(messageId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showLongToast(String message){
        if(toast == null){
            synchronized (ToastUtil.class) {
                if (null == toast){
                    toast = Toast.makeText(GalbsApplication.getGalbsApplication(), message, Toast.LENGTH_LONG);
                }
            }
            toast.show() ;
            oneTime = System.currentTimeMillis() ;
        }else{
            twoTime = System.currentTimeMillis() ;
            if(message.equals(oldMsg)){
                if(twoTime - oneTime > toast.getDuration()){
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show() ;
                }
            }else{
                toast.setDuration(Toast.LENGTH_LONG);
                oldMsg = message ;
                toast.setText(message) ;
                toast.show() ;
            }
        }
        oneTime = twoTime ;
    }
}
