package com.android.mvp.demo.data.db.impl;


import com.android.mvp.demo.data.db.PupilInfoHelper;
import com.android.mvp.demo.data.db.bean.PupilInfo;
import com.android.mvp.demo.data.db.bean.PupilInfo_;

import java.util.List;

import io.objectbox.Box;

/**
 * 作者: 刘康
 * 时间: 2017/12/14 0014 16:57
 */

public class PupilInfoHelperImpl extends BaseDbHelperImpl implements PupilInfoHelper {

    @Override
    public void putPupilInfo(PupilInfo pupilInfo) {
        if (null == pupilInfo) {
            return;
        }
        Box<PupilInfo> pupilInfoBox = getBox(PupilInfo.class);
        PupilInfo existPupil = pupilInfoBox.query().equal(PupilInfo_.phone, pupilInfo.getPhone()).build().findFirst();
        if (null != existPupil) {
            //当前是数据库已经存在该用户
            pupilInfo.setId(existPupil.getId());
        }
        pupilInfoBox.put(pupilInfo);
    }

    @Override
    public void setCurrentPupil(PupilInfo currentPupil) {
        if (null == currentPupil) {
            return;
        }
        //将之前设置的当前用户标准位设置为false
        Box<PupilInfo> pupilInfoBox = getBox(PupilInfo.class);
        List<PupilInfo> pupilInfos = pupilInfoBox.query().equal(PupilInfo_.isCurrent, true).build().find();
        if (null != pupilInfos && pupilInfos.size() > 0) {
            for (PupilInfo pupil : pupilInfos) {
                pupil.setIsCurrent(false);
                pupilInfoBox.put(pupil);
            }
        }

        //将要设置的用户的isCurrent标志位设置为true
        PupilInfo currPupil = pupilInfoBox.query().equal(PupilInfo_.phone, currentPupil.getPhone()).build().findFirst();
        if (null != currPupil) {
            currPupil.setIsCurrent(true);
            pupilInfoBox.put(currPupil);
        } else {//当前数据库不存在该被监护人
            currentPupil.setIsCurrent(true);
            pupilInfoBox.put(currentPupil);
        }
    }

    @Override
    public PupilInfo getCurrentPupil() {
        Box<PupilInfo> pupilInfoBox = getBox(PupilInfo.class);
        PupilInfo currPupil = pupilInfoBox.query().equal(PupilInfo_.isCurrent, true).build().findFirst();
        if (null == currPupil) {
            throw new NullPointerException("current pupil is not exist!");
        } else {
            return currPupil;
        }
    }

    @Override
    public PupilInfo findPupilInfoByDeviceNumber(String deviceNumber) {
        Box<PupilInfo> pupilInfoBox = getBox(PupilInfo.class);
        List<PupilInfo> pupilInfoList = pupilInfoBox.find(PupilInfo_.phone, deviceNumber);
        if (null != pupilInfoList && pupilInfoList.size() > 0) {
            return pupilInfoList.get(0);
        } else {
            throw new NullPointerException("this pupil does not exist");
        }
    }

    @Override
    public List<PupilInfo> getAllPulilInfo(String appNumber) {
        Box<PupilInfo> pupilInfoBox = getBox(PupilInfo.class);
        List<PupilInfo> pupilInfoList = pupilInfoBox.getAll();
        return pupilInfoList;
    }

    @Override
    public void deletePupilInfo(PupilInfo pupilInfo) {
        Box<PupilInfo> pupilInfoBox = getBox(PupilInfo.class);
        pupilInfoBox.remove(pupilInfo);
    }

    @Override
    public void deletePupilInfo(String deviceNumber) {
        Box<PupilInfo> pupilInfoBox = getBox(PupilInfo.class);

        pupilInfoBox.remove(findPupilInfoByDeviceNumber(deviceNumber));
    }


}
