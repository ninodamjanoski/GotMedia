package com.mobgen.gotmedia.core.lists;

import android.view.View;

public class FocusHighlightHelper {
    public static final int ZOOM_FACTOR_LARGE = 3;
    public static final int ZOOM_FACTOR_MEDIUM = 2;
    public static final int ZOOM_FACTOR_NONE = 0;
    public static final int ZOOM_FACTOR_SMALL = 1;

    public interface FocusHighlightHandler {
        void onInitializeView(View view);

        void onItemFocused(View view, boolean z);
    }
}
