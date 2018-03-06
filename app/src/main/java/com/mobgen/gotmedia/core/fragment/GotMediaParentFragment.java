package com.mobgen.gotmedia.core.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.mobgen.gotmedia.core.fragment.base.FragmentBase;

/**
 * Created on 3/1/18.
 */

public abstract class GotMediaParentFragment extends FragmentBase {

    protected void addChildFragment(Fragment fragment, int viewGroupId, String tag) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(viewGroupId, fragment, tag);
        ft.commit();
    }

    protected boolean hasChildFragmentWithTag(String tag) {
        return getChildFragmentManager().findFragmentByTag(tag) != null;
    }
}
