package com.android.mvp.demo.data.db.base;

import io.objectbox.Box;

/**
 * 作者: 刘康
 * 时间: 2017/12/14 0014 17:03
 */

public interface BaseDbHelper {
    public  <T>Box getBox(Class<T> tClass);
}
