package com.android.mvp.demo.data.db;

import com.android.mvp.demo.data.db.base.BaseDbHelper;
import com.android.mvp.demo.data.db.bean.PupilInfo;

import java.util.List;

/**
 * 时间: 2017/12/14 0014 17:01
 */

public interface PupilInfoHelper extends BaseDbHelper {

    public void putPupilInfo(PupilInfo pupilInfo);

    public void setCurrentPupil(PupilInfo currentPupil);

    public PupilInfo getCurrentPupil() ;

    public PupilInfo findPupilInfoByDeviceNumber(String deviceNumber);

    public List<PupilInfo> getAllPulilInfo(String appNumber);

    public void deletePupilInfo(PupilInfo pupilInfo);

    public void deletePupilInfo(String deviceNumber);
}
