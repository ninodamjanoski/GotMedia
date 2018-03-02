package com.mobgen.gotmedia.core.adapter;

import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.ObjectAdapter.DataObserver;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;


import com.mobgen.gotmedia.core.listener.TimedDebouncingOnClickListener;
import com.mobgen.gotmedia.core.lists.ChildClickPresenter;
import com.mobgen.gotmedia.core.lists.FocusHighlightHelper;

import java.util.ArrayList;

public class RecyclerViewAdapter extends Adapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "RecyclerViewAdapter";
    private ObjectAdapter adapter;
    OnClickListener clickListener;
    private DataObserver dataObserver;
    private FocusHighlightHelper.FocusHighlightHandler focusHighlight;
    private ChildClickPresenter.OnCellChildClickListener onCellChildClickListener;
    private OnItemClickedListener onItemClickedListener;
    private PresenterSelector presenterSelector;
    private ArrayList<Presenter> presenters;
    @Deprecated
    private boolean useTagForPosition;

    public interface OnItemClickedListener {
        void onItemClicked(View view, Object obj, int i);
    }

    class RecyclerViewAdapterClickListener extends TimedDebouncingOnClickListener {

        public void doClick(View view) {
            handleItemClick(view);
        }
    }

    class OnItemClickListener implements OnClickListener {

        public void onClick(View view) {

        }
    }

    class RecyclerViewDataObserver extends DataObserver {

        public void onChanged() {
            notifyDataSetChanged();
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            notifyItemRangeChanged(positionStart, itemCount);
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            notifyItemRangeInserted(positionStart, itemCount);
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            notifyItemRangeRemoved(positionStart, itemCount);
        }
    }

    public RecyclerViewAdapter(ObjectAdapter adapter, PresenterSelector presenterSelector,
                               boolean useTagForPosition, boolean debounce, boolean legacy) {
        OnClickListener onClickListener;
        presenters = new ArrayList();
        this.useTagForPosition = false;
        dataObserver = new RecyclerViewDataObserver();
        if (legacy) {
            this.adapter = adapter;
            this.presenterSelector = presenterSelector;
            this.useTagForPosition = useTagForPosition;
        } else {
            setAdapter(adapter);
            this.presenterSelector = presenterSelector;
        }
        if (debounce) {
            onClickListener = new RecyclerViewAdapterClickListener();
        } else {
            onClickListener = new OnItemClickListener();
        }
        clickListener = onClickListener;
    }

    private void handleItemClick(View view) {
        if (onItemClickedListener != null) {
            int pos;
            if (useTagForPosition) {
                pos = ((Integer) view.getTag()).intValue();
            } else {
                pos = ((RecyclerView.ViewHolder) view.getTag()).getAdapterPosition();
            }
            if (pos >= 0 && adapter.size() > pos) {
                onItemClickedListener.onItemClicked(view, adapter.get(pos), pos);
            }
        }
    }

    @Deprecated
    public RecyclerViewAdapter(ObjectAdapter adapter, PresenterSelector presenterSelector, boolean useTagForPosition) {
        this(adapter, presenterSelector, useTagForPosition, true, true);
    }

    public RecyclerViewAdapter(ObjectAdapter adapter, PresenterSelector presenterSelector) {
        this(adapter, presenterSelector, false, true, false);
    }

    public void setAdapter(ObjectAdapter adapter) {
        try {
            adapter.unregisterObserver(dataObserver);
        } catch (Exception e) {
        }
        this.adapter = adapter;
        if (this.adapter != null) {
            this.adapter.registerObserver(dataObserver);
            if (hasStableIds() != this.adapter.hasStableIds()) {
                setHasStableIds(this.adapter.hasStableIds());
            }
        }
    }

    public boolean isEmpty() {
        return adapter == null || adapter.size() == 0;
    }

    public void clear() {
        setAdapter(null);
    }

    public int getItemCount() {
        if (adapter == null) {
            return 0;
        }
        return adapter.size();
    }

    public int getItemViewType(int position) {
        if (adapter.size() <= position) {
            return -1;
        }
        Presenter presenter = (presenterSelector != null ? presenterSelector : adapter.getPresenterSelector()).getPresenter(getItemAt(position));
        int type = presenters.indexOf(presenter);
        if (type >= 0) {
            return type;
        }
        presenters.add(presenter);
        return presenters.indexOf(presenter);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Presenter presenter = (Presenter) presenters.get(viewType);
        if (presenter instanceof ChildClickPresenter) {
            ((ChildClickPresenter) presenter).setOnCellChildClickListener(onCellChildClickListener);
        }
        Presenter.ViewHolder presenterVh = presenter.onCreateViewHolder(parent);
        View view = presenterVh.view;
        ViewHolder viewHolder = new ViewHolder(this, presenter, view, presenterVh);
        View presenterView = viewHolder.holder.view;
        if (presenterView != null) {
            viewHolder.focusChangeListener.chainedListener = presenterView.getOnFocusChangeListener();
            presenterView.setOnFocusChangeListener(viewHolder.focusChangeListener);
        }
        if (focusHighlight != null) {
            focusHighlight.onInitializeView(view);
        }
        return viewHolder;
    }

    public void setFocusHighlight(FocusHighlightHelper.FocusHighlightHandler listener) {
        focusHighlight = listener;
    }

    public OnItemClickedListener getOnItemClickedListener() {
        return onItemClickedListener;
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    public ChildClickPresenter.OnCellChildClickListener getOnCellChildClickListener() {
        return onCellChildClickListener;
    }

    public void setOnCellChildClickListener(ChildClickPresenter.OnCellChildClickListener onCellChildClickListener) {
        this.onCellChildClickListener = onCellChildClickListener;
    }

    public Object getItemAt(int position) {
        return adapter.get(position);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.item = getItemAt(position);
        holder.itemView.setOnClickListener(clickListener);
        viewHolder.presenter.onBindViewHolder(viewHolder.holder, viewHolder.item);
        if (useTagForPosition) {
            holder.itemView.setTag(Integer.valueOf(position));
        } else {
            holder.itemView.setTag(holder);
        }
    }

    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.presenter.onUnbindViewHolder(viewHolder.holder);
        viewHolder.item = null;
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.presenter.onViewAttachedToWindow(viewHolder.holder);
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.presenter.onViewDetachedFromWindow(viewHolder.holder);
    }

    public long getItemId(int position) {
        return adapter.getId(position);
    }

    final class OnFocusChangeListener implements View.OnFocusChangeListener {
        View.OnFocusChangeListener chainedListener;
        final /* synthetic */ RecyclerViewAdapter recyclerViewAdapter;

        OnFocusChangeListener(RecyclerViewAdapter recyclerViewAdapter) {
            this.recyclerViewAdapter = recyclerViewAdapter;
        }

        public void onFocusChange(View view, boolean hasFocus) {
            if (view != null) {
                focusHighlight.onItemFocused(view, hasFocus);
            }
            if (this.chainedListener != null) {
                this.chainedListener.onFocusChange(view, hasFocus);
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final Presenter.ViewHolder holder;
        private RecyclerViewAdapter recyclerViewAdapter;
        final OnFocusChangeListener focusChangeListener =
                new OnFocusChangeListener(recyclerViewAdapter);
        Object item;
        public final Presenter presenter;

        public final Presenter getPresenter() {
            return this.presenter;
        }

        public final Presenter.ViewHolder getViewHolder() {
            return this.holder;
        }

        public final Object getItem() {
            return this.item;
        }

        ViewHolder(RecyclerViewAdapter recyclerViewAdapter, Presenter presenter, View view, Presenter.ViewHolder holder) {
            super(view);
            this.recyclerViewAdapter = recyclerViewAdapter;
            this.presenter = presenter;
            this.holder = holder;
        }
    }

}
