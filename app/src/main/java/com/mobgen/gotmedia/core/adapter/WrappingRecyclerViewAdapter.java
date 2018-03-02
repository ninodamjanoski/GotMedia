package com.mobgen.gotmedia.core.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;

public abstract class WrappingRecyclerViewAdapter<T extends ViewHolder> extends Adapter<T> {
    protected final Adapter wrappedAdapter;

    protected abstract boolean isPartOfWrappedAdapter(ViewHolder viewHolder);

    public WrappingRecyclerViewAdapter(Adapter wrappedAdapter) {
        this.wrappedAdapter = wrappedAdapter;
    }

    public void notifyDataSetChangedIncludingWrapped() {
        this.wrappedAdapter.notifyDataSetChanged();
        notifyDataSetChanged();
    }

    public void onViewRecycled(ViewHolder holder) {
        if (isPartOfWrappedAdapter(holder)) {
            this.wrappedAdapter.onViewDetachedFromWindow(holder);
        }
    }

    public void onViewAttachedToWindow(ViewHolder holder) {
        if (isPartOfWrappedAdapter(holder)) {
            this.wrappedAdapter.onViewDetachedFromWindow(holder);
        }
    }

    public void onViewDetachedFromWindow(ViewHolder holder) {
        if (isPartOfWrappedAdapter(holder)) {
            this.wrappedAdapter.onViewDetachedFromWindow(holder);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.wrappedAdapter.onAttachedToRecyclerView(recyclerView);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.wrappedAdapter.onDetachedFromRecyclerView(recyclerView);
    }
}
