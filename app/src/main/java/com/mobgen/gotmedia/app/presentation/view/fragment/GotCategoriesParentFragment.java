package com.mobgen.gotmedia.app.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobgen.gotmedia.R;
import com.mobgen.gotmedia.app.presentation.categories.CategoriesFragment;
import com.mobgen.gotmedia.app.presentation.categories.CategoryListFragment;
import com.mobgen.gotmedia.app.presentation.splash.SplashFragment;
import com.mobgen.gotmedia.core.fragment.GotMediaParentFragment;
import com.mobgen.gotmedia.core.fragment.callback.BackAndUpNavigator;

import javax.inject.Inject;

/**
 * Created on 3/1/18.
 */

public class GotCategoriesParentFragment extends GotMediaParentFragment implements FragmentNavListener, BackAndUpNavigator {
    
    public static final String TAG = "GotCategoriesParentFragment";
    private CategoriesFragment categoriesFragment;
    private SplashFragment splashFragment;


    public static GotCategoriesParentFragment newInstance(){
        GotCategoriesParentFragment carMediaParentFragment = new GotCategoriesParentFragment();
        Bundle args = new Bundle();
        carMediaParentFragment.setArguments(args);
        return carMediaParentFragment;
    }

    @Inject
    public GotCategoriesParentFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gotcategories_parent, container, false);
        initFragments();
        getChildFragmentManager().executePendingTransactions();
        return root;
    }

    private void initFragments() {
        if(!hasChildFragmentWithTag(CategoriesFragment.TAG)){
            categoriesFragment = CategoriesFragment.newInstance();
        }
        if (!hasChildFragmentWithTag(SplashFragment.TAG)) {
            splashFragment = SplashFragment.newInstance();
            addChildFragment(splashFragment, R.id.activityContent, SplashFragment.TAG);
        }

    }

    @Override
    public void onOpenCategories() {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.remove(splashFragment);
        ft.commit();
        addChildFragment(categoriesFragment, R.id.activityContent, CategoriesFragment.TAG);
    }

    @Override
    public boolean onBackNavigation() {
        CategoryListFragment overFragment = (CategoryListFragment) getChildFragmentManager().findFragmentById(R.id.overFragmentHolder);
        if(overFragment != null){
            getChildFragmentManager().popBackStackImmediate();
            return true;
        }
        return false;
    }

    @Override
    public boolean onUpNavigation() {
        return false;
    }
}
