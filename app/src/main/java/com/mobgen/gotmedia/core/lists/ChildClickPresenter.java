package com.mobgen.gotmedia.core.lists;

import android.support.v17.leanback.widget.Presenter;
import android.view.View;

public abstract class ChildClickPresenter extends Presenter {
    private OnCellChildClickListener onCellChildClickListener;

    public interface OnCellChildClickListener {
        void onViewClicked(View view, Object obj);
    }

    public OnCellChildClickListener getOnCellChildClickListener() {
        return this.onCellChildClickListener;
    }

    public void setOnCellChildClickListener(OnCellChildClickListener onCellChildClickListener) {
        this.onCellChildClickListener = onCellChildClickListener;
    }
}
