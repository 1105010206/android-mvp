package com.android.mvp.demo.utils;

import android.content.Context;


import com.android.mvp.demo.data.protocol.request.base.GalbsMsg;
import com.android.mvp.demo.data.protocol.request.base.ProtocolHead;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;



/**
 * 时间: 2017/12/18 0018 16:49
 */

public class RequestContentUtils {

    public final static String MULTIPART_FORM_DATA_IMAGE = "image/jpg";

    public final static String MULTIPART_FORM_DATA_AUDIO = "multipart/form-data";

    private static final int REQ_NUMBER_OF_PAGE = 9;//最多显示的产品数目

    public static MultipartBody.Part prepareFilePart(String partName, String path) {
        File file = new File(path);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA_AUDIO), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
    /**
     * @param context
     * @description 登录
     * @param
     */
    public static GalbsMsg getAppLogInContent(Context context,String userNumber,String passWord) {

        GalbsMsg msg = null;
        /*具体实例化
        * */
        return msg;
    }

}
