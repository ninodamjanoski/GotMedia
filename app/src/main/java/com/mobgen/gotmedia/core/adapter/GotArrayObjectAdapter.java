package com.mobgen.gotmedia.core.adapter;

import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.DiffUtil.DiffResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GotArrayObjectAdapter extends ObjectAdapter {
    private ArrayList<Object> items = new ArrayList();

    class DiffCallback extends DiffUtil.Callback {

        public int getOldListSize() {
            return size();
        }

        public int getNewListSize() {
            return items.size();
        }

        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return get(oldItemPosition).getClass().equals(items.get(newItemPosition).getClass());
        }

        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return get(oldItemPosition).equals(items.get(newItemPosition));
        }
    }


    public GotArrayObjectAdapter() {
        super();
    }

    public GotArrayObjectAdapter(PresenterSelector presenterSelector) {
        super(presenterSelector);
    }

    public GotArrayObjectAdapter(Presenter presenter) {
        super(presenter);
    }

    public int size() {
        return this.items.size();
    }

    public Object get(int index) {
        return this.items.get(index);
    }

    public int indexOf(Object item) {
        return this.items.indexOf(item);
    }

    public void add(Object item) {
        add(this.items.size(), item);
    }

    public void add(int index, Object item) {
        this.items.add(index, item);
        notifyItemRangeInserted(index, 1);
    }

    public void addAll(int index, Collection items) {
        int itemsCount = items.size();
        if (itemsCount != 0) {
            this.items.addAll(index, items);
            notifyItemRangeInserted(index, itemsCount);
        }
    }

    public boolean remove(Object item) {
        int index = this.items.indexOf(item);
        if (index >= 0) {
            this.items.remove(index);
            notifyItemRangeRemoved(index, 1);
        }
        if (index >= 0) {
            return true;
        }
        return false;
    }

    public void replace(int position, Object item) {
        this.items.set(position, item);
        notifyItemRangeChanged(position, 1);
    }

    public int removeItems(int position, int count) {
        int itemsToRemove = Math.min(count, this.items.size() - position);
        if (itemsToRemove <= 0) {
            return 0;
        }
        for (int i = 0; i < itemsToRemove; i++) {
            this.items.remove(position);
        }
        notifyItemRangeRemoved(position, itemsToRemove);
        return itemsToRemove;
    }

    public void clear() {
        int itemCount = this.items.size();
        if (itemCount != 0) {
            this.items.clear();
            notifyItemRangeRemoved(0, itemCount);
        }
    }

    public void setData(Collection items) {
        this.items.clear();
        this.items.addAll(items);
        notifyChanged();
    }

    public DiffResult setDataWithDiff(List items) {
        DiffResult diff = DiffUtil.calculateDiff(new DiffCallback());
        this.items.clear();
        this.items.addAll(items);
        return diff;
    }

    public void move(int oldPosition, int newPosition, Object item) {
        removeItems(oldPosition, 1);
        add(newPosition, item);
    }

    public void notifyDataChanged(int idx) {
        notifyItemRangeChanged(idx, 1);
    }

    public <E> List<E> unmodifiableList() {
        return (List<E>) Collections.unmodifiableList(this.items);
    }
}
