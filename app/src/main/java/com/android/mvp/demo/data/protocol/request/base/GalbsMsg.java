package com.android.mvp.demo.data.protocol.request.base;

public interface GalbsMsg extends BaseMsg{
	
	int NO_PUSH_FLAG = -1;
	/**
	 * @description 获取消息头
	 * @return 消息头
	 */
	public ProtocolHead getProtocolHead();
	
	/**
	 * 设置推送模式
	 * @param isPush ：ture:推送 false:不推送
	 * @description 设置推送模式
	 * @param
	 */
	public void setPushMode(boolean isPush);
	
	public int getPushMode();
}
