package com.android.mvp.demo.data.protocol.response;

/**
 * 作者: 刘康
 * 时间: 2017/12/8 0008 14:43
 */

public class LogInResponse {

    /**
     * isEdit : 1
     * hdUrl : http://gaappapi.taichenda.com:8092/LBS/SingleFile/2017/11/201711221945358638206.jpg
     * nickName :
     * email :
     * exist : 1
     * phone : 15919759914
     * gPhone : 13040854264
     * online : 1
     * bSoftVersion : 2800
     * bIsValidity : 1
     * endValidTime : 2117-03-25
     * localization : 0
     * groupId : 460029198314582
     * imsi : 460029198314582
     * countryCode : 86
     * state : 1
     * serverTime : 2017-12-14 13:52:45
     */

    private int isEdit;
    private String hdUrl;
    private String nickName;
    private String email;
    private int exist;
    private String phone;
    private String gPhone;
    private int online;
    private int bSoftVersion;
    private int bIsValidity;
    private String endValidTime;
    private int localization;
    private String groupId;
    private String imsi;
    private String countryCode;
    private int state;
    private String serverTime;

    public LogInResponse(int isEdit, String hdUrl, String nickName, String email,
                         int exist, String phone, String gPhone, int online,
                         int bSoftVersion, int bIsValidity, String endValidTime,
                         int localization, String groupId, String imsi, String countryCode,
                         int state, String serverTime) {
        this.isEdit = isEdit;
        this.hdUrl = hdUrl;
        this.nickName = nickName;
        this.email = email;
        this.exist = exist;
        this.phone = phone;
        this.gPhone = gPhone;
        this.online = online;
        this.bSoftVersion = bSoftVersion;
        this.bIsValidity = bIsValidity;
        this.endValidTime = endValidTime;
        this.localization = localization;
        this.groupId = groupId;
        this.imsi = imsi;
        this.countryCode = countryCode;
        this.state = state;
        this.serverTime = serverTime;
    }

    public int getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(int isEdit) {
        this.isEdit = isEdit;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExist() {
        return exist;
    }

    public void setExist(int exist) {
        this.exist = exist;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGPhone() {
        return gPhone;
    }

    public void setGPhone(String gPhone) {
        this.gPhone = gPhone;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getBSoftVersion() {
        return bSoftVersion;
    }

    public void setBSoftVersion(int bSoftVersion) {
        this.bSoftVersion = bSoftVersion;
    }

    public int getBIsValidity() {
        return bIsValidity;
    }

    public void setBIsValidity(int bIsValidity) {
        this.bIsValidity = bIsValidity;
    }

    public String getEndValidTime() {
        return endValidTime;
    }

    public void setEndValidTime(String endValidTime) {
        this.endValidTime = endValidTime;
    }

    public int getLocalization() {
        return localization;
    }

    public void setLocalization(int localization) {
        this.localization = localization;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    @Override
    public String toString() {
        return "LogInResponse{" +
                "isEdit=" + isEdit +
                ", hdUrl='" + hdUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", exist=" + exist +
                ", phone='" + phone + '\'' +
                ", gPhone='" + gPhone + '\'' +
                ", online=" + online +
                ", bSoftVersion=" + bSoftVersion +
                ", bIsValidity=" + bIsValidity +
                ", endValidTime='" + endValidTime + '\'' +
                ", localization=" + localization +
                ", groupId='" + groupId + '\'' +
                ", imsi='" + imsi + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", state=" + state +
                ", serverTime='" + serverTime + '\'' +
                '}';
    }
}
