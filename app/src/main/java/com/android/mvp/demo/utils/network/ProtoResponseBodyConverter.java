package com.android.mvp.demo.utils.network;

import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 作者: 刘康
 * 时间: 2017/12/7 0007 11:25
 */

final class ProtoResponseBodyConverter<T> implements Converter<ResponseBody, T> {
   // private final Parser<T> parser;
   private final TypeAdapter<T> adapter;
    ProtoResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        //转换成对象
        return adapter.fromJson(value.string());
    }
}
