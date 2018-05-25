package com.android.mvp.demo.presentor.base;

import android.os.Bundle;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2018/3/29.
 */

public class BaseFragmentPresentor implements BaseFragmentPresentorI {
    public String TAG = getClass().getName();
    public CompositeSubscription compositeSubscription;
    @Override
    public void onViewCreated(Bundle savedInstanceState) {
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onViewDestroy() {
        if (compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }
}
