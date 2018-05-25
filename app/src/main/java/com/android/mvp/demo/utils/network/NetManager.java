package com.android.mvp.demo.utils.network;

import android.content.Context;

import com.android.mvp.demo.data.protocol.request.base.GalbsMsg;
import com.android.mvp.demo.data.protocol.response.LogInResponse;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class NetManager {
    private RetrofitService mRetrofitService;
    public NetManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    /*登录*/
    public Observable<LogInResponse> appLogIn(GalbsMsg msg){
        return mRetrofitService.appLogIn(msg);
    }

}
