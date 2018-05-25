package com.android.mvp.demo.presentor.base;

import android.os.Bundle;

/**
 * Created by Administrator on 2018/3/29.
 */

public interface BaseFragmentPresentorI {
    public void  onViewCreated(Bundle savedInstanceState);

    public void onViewDestroy();
}
