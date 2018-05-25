package com.android.mvp.demo.data.db.bean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * 作者: 刘康
 * 时间: 2017/12/5 0005 9:05
 */
@Entity
public class PupilInfo {

    @Id
    private long id;
    private String phone;
    private String nickName;
    private String hdUrl;
    private String email;
    private boolean isCurrent;
    private boolean isOnline;
    private String password;
    private boolean isTrack;
    private int bIsValidity;
    private int authorization;
    private String endValidTime;
    private String logoUrl;
    private String productName;
    private int localization;
    private String imsi;
    private String countryCode;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public PupilInfo() {
    }

    public PupilInfo(Long id) {
        this.id = id;
    }

    public PupilInfo(Long id, String phone, String nickName, String hdUrl, String email, boolean isCurrent, boolean isOnline, String password, boolean isTrack, int bIsValidity, int authorization, String endValidTime, String logoUrl, String productName, int localization, String imsi, String countryCode) {
        this.id = id;
        this.phone = phone;
        this.nickName = nickName;
        this.hdUrl = hdUrl;
        this.email = email;
        this.isCurrent = isCurrent;
        this.isOnline = isOnline;
        this.password = password;
        this.isTrack = isTrack;
        this.bIsValidity = bIsValidity;
        this.authorization = authorization;
        this.endValidTime = endValidTime;
        this.logoUrl = logoUrl;
        this.productName = productName;
        this.localization = localization;
        this.imsi = imsi;
        this.countryCode = countryCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsTrack() {
        return isTrack;
    }

    public void setIsTrack(boolean isTrack) {
        this.isTrack = isTrack;
    }

    public int getBIsValidity() {
        return bIsValidity;
    }

    public void setBIsValidity(int bIsValidity) {
        this.bIsValidity = bIsValidity;
    }

    public int getAuthorization() {
        return authorization;
    }

    public void setAuthorization(int authorization) {
        this.authorization = authorization;
    }

    public String getEndValidTime() {
        return endValidTime;
    }

    public void setEndValidTime(String endValidTime) {
        this.endValidTime = endValidTime;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getLocalization() {
        return localization;
    }

    public void setLocalization(int localization) {
        this.localization = localization;
    }

    public String getImsi(){
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getCountryCode(){
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    // KEEP METHODS - put your custom methods here
    public PupilInfo(String phone, String nickName, String hdUrl, String email, boolean isCurrent, boolean isOnline, String password, boolean isTrack, int bIsValidity, int authorization, String endValidTime, String logoUrl, String productName, int localization, String imsi, String countryCode) {
        this.phone = phone;
        this.nickName = nickName;
        this.hdUrl = hdUrl;
        this.email = email;
        this.isCurrent = isCurrent;
        this.isOnline = isOnline;
        this.password = password;
        this.isTrack = isTrack;
        this.bIsValidity = bIsValidity;
        this.authorization = authorization;
        this.endValidTime = endValidTime;
        this.logoUrl = logoUrl;
        this.productName = productName;
        this.localization = localization;
        this.imsi = imsi;
        this.countryCode = countryCode;
    }

}
