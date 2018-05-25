package com.android.mvp.demo.data.db.impl;


import com.android.mvp.demo.data.db.PupilInfoHelper;

/**
 * 时间: 2017/12/14 0014 11:24
 */

public class GalbsDBManager {


    private volatile PupilInfoHelper pupilInfoHelper = null;


    public GalbsDBManager() {

    }

    public PupilInfoHelper getPupilInfoHelper(){
        if (null == pupilInfoHelper) {
            synchronized (GalbsDBManager.class) {
                if (null == pupilInfoHelper) {
                    pupilInfoHelper = new PupilInfoHelperImpl();
                }
            }
        }
        return pupilInfoHelper;
    }

}
