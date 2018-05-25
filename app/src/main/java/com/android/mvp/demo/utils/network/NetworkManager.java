/*
 * © [2013-12-27] by taichenda CO.,LTD.
 * @title NetworkManager.java 
 * @package com.tcd.appstore.commons.util
 * @author David [QQ:375767588] 
 * @update 修改时间 2013-12-27 下午1:39:22
 * @version V1.0 
 */
package com.android.mvp.demo.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.lang.reflect.Method;

/**
 * @description 网络管理类
 * @version 1.0
 * @author David [QQ:375767588]
 * @update 修改时间 2013-12-27 下午1:39:22
 */
public class NetworkManager {
	/**
	 * WIFI 管理
	 */
	private WifiManager wifiManager;

	/**
	 * 网络连接管理
	 */
	private ConnectivityManager connManager;
	/**
	 * wifi 状态信息
	 */
	private NetworkInfo wifiInfo;
	/**
	 * gprs 状态信息
	 */
	private NetworkInfo mobileInfo;

	private static final String getMobileDataEnabled = "getMobileDataEnabled";
	
	private static final String setMobileDataEnabled = "setMobileDataEnabled";

	/**
	 * 类的构造方法
	 */
	public NetworkManager(Context context) {
		connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		this.wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);// 获取Wifi服务
		wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		mobileInfo = connManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	}

	/**
	 * 检查WIFI是否可用
	 * 
	 * @param
	 * @return
	 */
	public boolean isWifiAvailable() {
		wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return wifiInfo.isAvailable();
	}

	/**
	 * @description wifi是否打开
	 * @return
	 */
	public boolean wifiEnabled() {
		return wifiManager.isWifiEnabled();
	}

	/**
	 * @description 检查WIFI是否连上。
	 * @return
	 */
	public boolean isWifiConnected() {
		// 每次检测都需要重新获取NetworkInfo对象，否则状态不会时时更新
		wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return wifiInfo.isConnected();
	}

	/**
	 * 检查移动数据是否可用。如飞行模式下则不可用，返回false
	 * 
	 * @param
	 * @return
	 */
	public boolean isMobileAvailable() {
		mobileInfo = connManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return mobileInfo.isAvailable();
	}

	/**
	 * @return gprs 是否打开
	 */
	public boolean gprsEnabled() {
		Class<? extends ConnectivityManager> cmClass = connManager.getClass();
		Class<?>[] argClasses = null;
		Object[] argObject = null;
		Boolean isOpen = false;
		try {
			Method method = cmClass.getMethod(getMobileDataEnabled, argClasses);
			isOpen = (Boolean) method.invoke(connManager, argObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isOpen;
	}

	/**
	 * @description 移动网络是否连接上。
	 * @return
	 */
	public boolean isMobileConnected() {
		// 每次检测都需要重新获取NetworkInfo对象，否则状态不会时时更新
		mobileInfo = connManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return mobileInfo.isConnected();
	}

	/**
	 * 打开Gprs
	 * 
	 * @return
	 */
	public void openGprs() {
		setGprsEnabled(true);
	}

	/**
	 * @description 关闭Gprs
	 * @return
	 */
	public void closeGprs() {
		setGprsEnabled(false);
	}

	/**
	 * 打开wifi
	 * 
	 * @return
	 */
	public boolean openWifi() {
		return wifiManager.setWifiEnabled(true);
	}

	/**
	 * @description 关闭 wifi
	 * @return
	 */
	public boolean closeWifi() {
		return wifiManager.setWifiEnabled(false);
	}

	/**
	 * @description 获取当前wifi的信号强度
	 * @return
	 */
	public int getWifiSignalLevel() {
		// 得到Wifi信息
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();// 得到连接信息
		return WifiManager.calculateSignalLevel(wifiInfo.getLinkSpeed(), 4);
	}

	/**
	 * @description 开启/关闭GPRS
	 * @param
	 * @param isEnable
	 */
	private void setGprsEnabled(boolean isEnable) {
		Class<? extends ConnectivityManager> cmClass = connManager.getClass();
		Class<?>[] argClasses = new Class[1];
		argClasses[0] = boolean.class;
		try {
			Method method = cmClass.getMethod(setMobileDataEnabled, argClasses);
			method.invoke(connManager, isEnable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
