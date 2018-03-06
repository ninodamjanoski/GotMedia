package com.mobgen.gotmedia.core.fragment.base;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import dagger.android.support.DaggerFragment;

/**
 * Created on 3/1/18.
 */

public abstract class FragmentBase extends DaggerFragment {

    protected <T> T getFragmentListener(Context activity, Class<T> listenerClass) {
        if (getTargetFragment() != null && listenerClass.isInstance(getTargetFragment())) {
            return listenerClass.cast(getTargetFragment());
        }
        if (getParentFragment() != null && listenerClass.isInstance(getParentFragment())) {
            return listenerClass.cast(getParentFragment());
        }
        if (listenerClass.isInstance(activity)) {
            return listenerClass.cast(activity);
        }
        return null;
    }


    protected void showKeyboard(View v) {
        if (v != null && getActivity() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(v, 1);
            }
        }
    }

    protected void hideKeyboard(View v) {
        if (v != null && getActivity() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken() != null ? v.getWindowToken() : v.getApplicationWindowToken(), 0);
            }
        }
    }
}
