package com.mobgen.gotmedia.app.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobgen.gotmedia.R;
import com.mobgen.gotmedia.app.presentation.splash.SplashFragment;
import com.mobgen.gotmedia.core.fragment.GotMediaParentFragment;

import javax.inject.Inject;

/**
 * Created on 3/1/18.
 */

public class GotCategoriesParentFragment extends GotMediaParentFragment {
    
    public static final String TAG = "GotCategoriesParentFragment";


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
        SplashFragment attributesFragment;
        if (!hasChildFragmentWithTag(SplashFragment.TAG)) {
            attributesFragment = SplashFragment.newInstance();
            addChildFragment(attributesFragment, R.id.activityContent, SplashFragment.TAG);
        }

    }
}
