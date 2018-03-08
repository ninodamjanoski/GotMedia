package com.mobgen.gotmedia.core.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;

import com.mobgen.gotmedia.R;

import rx.functions.Func1;

public class UiUtil {
    private static final Func1<DisplayMetrics, Integer> heightFunc = new C41032();
    private static final Func1<DisplayMetrics, Integer> widthFunc = new C41021();

    static class C41021 implements Func1<DisplayMetrics, Integer> {
        C41021() {
        }

        public Integer call(DisplayMetrics displayMetrics) {
            return Integer.valueOf(displayMetrics.widthPixels);
        }
    }

    static class C41032 implements Func1<DisplayMetrics, Integer> {
        C41032() {
        }

        public Integer call(DisplayMetrics displayMetrics) {
            return Integer.valueOf(displayMetrics.heightPixels);
        }
    }

    public static int dpToPx(int dp, Context ctx) {
        return Math.round(((float) dp) * ctx.getResources().getDisplayMetrics().density);
    }

    public static float pxToDp(float px, Context ctx) {
        return px / ctx.getResources().getDisplayMetrics().density;
    }

    public static int getDeviceScreenWidthInDp(Context context) {
        if (context == null) {
            return -1;
        }
        return context.getResources().getConfiguration().screenWidthDp;
    }
    public static boolean isScreenSmallerThan320dp(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float scaleFactor = metrics.density;
        return Math.min(((float) metrics.widthPixels) / scaleFactor, ((float) metrics.heightPixels) / scaleFactor) <= 320.0f;
    }


    public static boolean isTabletLayout(Context context) {
        return context != null && context.getResources().getBoolean(R.bool.IsTablet);
    }

    public static boolean isDeviceStanding(Activity activity) {
        if (activity == null) {
            return true;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        if (metrics.widthPixels > metrics.heightPixels) {
            return false;
        }
        return true;
    }

    public static boolean isLandscape(Context context) {
        if (context != null && context.getResources().getConfiguration().orientation == 2) {
            return true;
        }
        return false;
    }

    public static int actionBarHeight(Context context) {
        TypedArray styledAttributes = context.obtainStyledAttributes(new int[]{16843499});
        int actionBarHeight = (int) styledAttributes.getDimension(0, 0.0f);
        styledAttributes.recycle();
        return actionBarHeight;
    }

    public static int getSystemBarSize(Context context) {
        try {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                return context.getResources().getDimensionPixelSize(resourceId);
            }
            return dpToPx(25, context);
        } catch (Throwable th) {
            return dpToPx(25, context);
        }
    }

    public static int getSoftButtonsBarWidth(WindowManager windowManager) {
        return getSoftButtonSize(windowManager, widthFunc);
    }

    public static int getSoftButtonsBarHeight(WindowManager windowManager) {
        return getSoftButtonSize(windowManager, heightFunc);
    }

    private static int getSoftButtonSize(WindowManager windowManager, Func1<DisplayMetrics, Integer> func1) {
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int usableWidth = ((Integer) func1.call(metrics)).intValue();
        windowManager.getDefaultDisplay().getRealMetrics(metrics);
        int realWidth = ((Integer) func1.call(metrics)).intValue();
        if (realWidth > usableWidth) {
            return realWidth - usableWidth;
        }
        return 0;
    }

    public static Activity getParentActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static void setMargin(MarginLayoutParams params, int start, int top, int end, int bottom) {
        params.setMargins(0, top, 0, bottom);
        params.setMarginStart(start);
        params.setMarginEnd(end);
    }
}
