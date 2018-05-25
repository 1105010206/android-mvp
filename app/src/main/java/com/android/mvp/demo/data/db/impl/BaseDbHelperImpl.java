package com.android.mvp.demo.data.db.impl;


import com.android.mvp.demo.GalbsApplication;
import com.android.mvp.demo.data.db.base.BaseDbHelper;

import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * 作者: 刘康
 * 时间: 2018/1/9 0009 11:46
 */

public class BaseDbHelperImpl implements BaseDbHelper {

    @Override
    public <T> Box getBox(Class<T> tClass) {
        BoxStore boxStore = GalbsApplication.getGalbsApplication().getBoxStore();
        Box<T> box = boxStore.boxFor(tClass);
        return box;

    }
}
