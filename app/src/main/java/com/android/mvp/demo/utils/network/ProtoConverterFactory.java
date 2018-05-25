package com.android.mvp.demo.utils.network;

import com.android.mvp.demo.data.protocol.request.base.BaseMsg;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 作者: 刘康
 * 时间: 2017/12/7 0007 11:23
 */

public final class ProtoConverterFactory extends Converter.Factory {

    public static ProtoConverterFactory create() {
        return create(new Gson());
    }

    public static ProtoConverterFactory create(Gson gson) {
        return new ProtoConverterFactory(gson);
    }

    private final Gson gson;

    private ProtoConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new ProtoResponseBodyConverter<>(adapter);
    }



    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        //进行条件判断，如果传进来的Type不是class，则匹配失败
//        if (!(type instanceof Class<?>)) {
//            return null;
//        }
//        //进行条件判断，如果传进来的Type不是MessageLite的实现类，则也匹配失败
//        if (!MessageLite.class.isAssignableFrom((Class<?>) type)) {
//            return null;
//        }
        return new ProtoRequestBodyConverter<BaseMsg>();
    }
}
