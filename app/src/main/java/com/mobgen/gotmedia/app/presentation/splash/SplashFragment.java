package com.mobgen.gotmedia.app.presentation.splash;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobgen.gotmedia.R;
import com.mobgen.gotmedia.app.presentation.view.fragment.FragmentNavListener;
import com.mobgen.gotmedia.app.presentation.splash.presenter.SplashContract;
import com.mobgen.gotmedia.core.fragment.base.FragmentBase;

import javax.inject.Inject;

/**
 * Created on 3/1/18.
 */

public class SplashFragment extends FragmentBase implements SplashContract.SplashView  {

    public static final String TAG = "SplashFragment";
    private FragmentNavListener fragmentNavListener;

    public static SplashFragment newInstance() {
        SplashFragment carMediaParentFragment = new SplashFragment();
        Bundle args = new Bundle();
        carMediaParentFragment.setArguments(args);
        return carMediaParentFragment;
    }
    
    @Inject
    SplashContract.SplashPresenter presenter;

    @Inject
    public SplashFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentNavListener = getFragmentListener(context, FragmentNavListener.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        presenter.takeView(this);
        return view;
    }


    public void openCategories() {
        fragmentNavListener.onOpenCategories();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }
}
