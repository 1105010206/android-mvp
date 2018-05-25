package com.android.mvp.demo.data.eventmsg;

/**
 * 时间: 2017/11/30 0030 11:16
 */

public class LogInMessage {
    private String message;
    public LogInMessage(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
