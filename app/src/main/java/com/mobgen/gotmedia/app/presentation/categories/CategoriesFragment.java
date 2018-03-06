package com.mobgen.gotmedia.app.presentation.categories;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mobgen.gotmedia.R;
import com.mobgen.gotmedia.app.entity.categories.CategoriesResult;
import com.mobgen.gotmedia.app.presentation.categories.listcell.CategoryCell;
import com.mobgen.gotmedia.app.presentation.categories.pojo.CategoryItem;
import com.mobgen.gotmedia.app.presentation.categories.presenter.CategoriesContract;
import com.mobgen.gotmedia.app.presentation.splash.presenter.SplashContract;
import com.mobgen.gotmedia.core.adapter.GtArrayObjectAdapter;
import com.mobgen.gotmedia.core.adapter.RecyclerViewAdapter;
import com.mobgen.gotmedia.core.fragment.base.FragmentBase;
import com.mobgen.gotmedia.core.lists.ChildClickPresenter;

import java.util.List;

import javax.inject.Inject;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created on 3/1/18.
 */

public class CategoriesFragment extends FragmentBase {


    public static final String TAG = "CategoriesFragment";
    private View toolbar;
    private View toolbarTitle;
    private View progressBar;
    private RecyclerView list;
    private GtArrayObjectAdapter adapterItems;
    private View errorText;
    private Button errorButton;
    @Inject
    CategoriesContract.CategoriesPresenter presenter;

    public static CategoriesFragment newInstance() {
        CategoriesFragment carMediaParentFragment = new CategoriesFragment();
        Bundle args = new Bundle();
        carMediaParentFragment.setArguments(args);
        return carMediaParentFragment;
    }

    @Inject
    public CategoriesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        toolbar = view.findViewById(R.id.activityToolbar);
        toolbarTitle = view.findViewById(R.id.toolBarTitle);
        progressBar = view.findViewById(R.id.progressBar);
        list = view.findViewById(R.id.list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        list.setItemAnimator(new SlideInLeftAnimator());
        adapterItems = new GtArrayObjectAdapter();
        RecyclerViewAdapter originalAdapter = new RecyclerViewAdapter(adapterItems, getListPresenter());
        originalAdapter.setOnCellChildClickListener(new ChildClickPresenter.OnCellChildClickListener() {
            @Override
            public void onViewClicked(View view, Object data) {
                presenter.onItemSelected();
            }
        });
        list.setAdapter(originalAdapter);
        errorText = view.findViewById(R.id.errorText);
        errorButton = view.findViewById(R.id.errorButton);
        errorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.visualizeData();
                updateStateForProgress();
            }
        });
        setToolbar();
        presenter.takeView(this);

        return view;
    }

    private PresenterSelector getListPresenter() {

        ClassPresenterSelector selector = new ClassPresenterSelector();
        selector.addClassPresenter(CategoryItem.class, new CategoryCell());
        return selector;
    }

    private void setToolbar() {

    }

    private void updateStateForProgress() {

    }

    public void showData(List<Object> categoriesResults) {
        adapterItems.setData(categoriesResults);
    }

    public void updateState(boolean isError) {
        if(isError){
            list.setVisibility(View.GONE);
            errorText.setVisibility(View.VISIBLE);
            errorButton.setVisibility(View.VISIBLE);
        }else {
            if(list.getVisibility() != View.VISIBLE){
                list.setVisibility(View.VISIBLE);
            }
            if(errorText.getVisibility() != View.GONE){
                errorText.setVisibility(View.GONE);
            }
            if(errorButton.getVisibility() != View.GONE){
                errorButton.setVisibility(View.GONE);
            }
        }
        progressBar.setVisibility(View.GONE);
    }
}
