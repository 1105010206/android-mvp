package com.android.mvp.demo.ui.dialog;

import android.widget.BaseAdapter;

public interface HolderAdapter extends Holder {

  void setAdapter(BaseAdapter adapter);

  void setOnItemClickListener(OnHolderListener listener);
}
