package com.android.mvp.demo.ui.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mvp.demo.presentor.base.BaseActivityPresentorI;
import com.android.mvp.demo.presentor.base.BaseFragmentPresentorI;
import com.android.mvp.demo.ui.viewinterface.base.BaseFragmentViewInterface;


/**
 * 时间: 2017/12/19 0019 16:47
 */

public abstract class BaseFragment extends Fragment implements BaseFragmentViewInterface {

    Activity activity;
    BaseFragmentPresentorI baseFragmentPresentorI;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        baseFragmentPresentorI = initViewPresentor();
        View view = onCreatFgView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (null != baseFragmentPresentorI) {
            baseFragmentPresentorI.onViewCreated(savedInstanceState);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != baseFragmentPresentorI) {
            baseFragmentPresentorI.onViewDestroy();
        }
    }

    protected abstract View onCreatFgView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState);

    protected abstract BaseFragmentPresentorI initViewPresentor();

    @Override
    public Activity getFGActivity(){
        return activity;
    }

    @Override
    public BaseActivityPresentorI getActivityPresentor(){
        return ((BaseActivity)activity).getViewPresentor();
    }

    @Override
    public BaseFragmentPresentorI getCurrentPresentor(){
        return baseFragmentPresentorI;
    }
}
