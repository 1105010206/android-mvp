package com.android.mvp.demo.presentor.base;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2018/3/14.
 */

public class BaseActivityPresentor implements BaseActivityPresentorI {

    protected CompositeSubscription mCompositeSubscription;

    public final String TAG = getClass().getName();
    @Override
    public void onViewCreated() {
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onViewDestroy() {
        if (null != mCompositeSubscription && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onTopRightMenuClick() {

    }
}
