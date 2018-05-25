package com.android.mvp.demo.data.protocol.request.base;


import com.android.mvp.demo.utils.ByteWrapper;

/**
 * Created by Administrator on 2018/3/26.
 */

public interface BaseMsg {

    /**
     * 协议消息封包
     *
     * @return ByteWrapper
     */
    public ByteWrapper wrap();

    /**
     * 获取整个消息的长度，包括消息头和消息体
     *
     * @return length
     */
    public int getWriteLength();
}
