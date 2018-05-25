package com.android.mvp.demo.utils.exception;

/**
 * 时间: 2017/12/8 0008 16:56
 */

public class ResultException extends RuntimeException {

    private int errCode = 0;

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}
