package com.android.mvp.demo.utils.network;



import com.android.mvp.demo.data.protocol.request.base.BaseMsg;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * 作者: 刘康
 * 时间: 2017/12/7 0007 11:25
 */

final class ProtoRequestBodyConverter<T extends BaseMsg> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/x-protobuf");

    @Override
    public RequestBody convert(T value) throws IOException {
        byte[] bytes = value.wrap().array();
//        String temp="1,1,0,0,39,117,50,48,49,55,49,50,48,56,49,49,49,52,53,49,35,-115,77,-100,42,-10,25,-32,11,-14,5,-127,4,-111,-55,77,0,0,0,0,0,0,0,0,0,0,0,52,54,48,48,49,48,56,53,52,48,54,49,54,51,55,0,0,0,0,0,0,0,0,0,0,0,0,116,99,100,116,101,115,116,0,0,0,0,0,0,0,116,99,100,73,110,105,116,0,0,0,0,0,0,0,0,0,0,0,78,-68,48,0,0,0,0,0,0,0,0,0,0,0,0,0,7,6,0,2,1,1,0,0,67,55,54,52,49,67,51,53,52,65,67,57,52,56,49,49,66,69,54,50,53,48,66,66,57,50,55,69,50,65,50,49,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,49,53,57,49,57,55,53,57,57,49,52,0,0,0,0,0,0,6,5,-34,84,40,-39,-82";
//        String[] str = temp.split(",");
//        byte[] tureBytes = new byte[str.length];
//        for(int i=0;i<str.length;i++) {
//            tureBytes[i] = Byte.valueOf(str[i]);
//        }
        //Log.d("get_test_net_content",Arrays.toString(bytes));
        //Log.d("get_test_net_content", "after="+Arrays.toString(tureBytes));
        return RequestBody.create(MEDIA_TYPE, bytes);
    }
}
