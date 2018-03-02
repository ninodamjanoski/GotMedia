package com.mobgen.gotmedia.core.utilities;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.widget.TextView;


public class CmTextUtil {


    public static final String COMMON_LINE_SEPARATOR = " - ";
    public static final String COMMON_COMMA_SEPARATOR = ", ";
    public static final String COMMON_COLON_SEPARATOR = " : ";
    public static final String COMMON_SEPARATOR = " ";
    public static final String OPEN_BRACKET = " (";
    public static final String CLOSE_BRACKET = ")";


    public static void addSuperScript(TextView text, String superscript) {
        SpannableString s = new SpannableString(superscript);
        s.setSpan(new SuperscriptSpan(), 0, s.length(), 33);
        s.setSpan(new RelativeSizeSpan(0.7f), 0, s.length(), 33);
        text.append(s);
    }

    public static CharSequence createToolbarTwoLineTitle(String title, String subTitle, int subTitleColor) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(title);
        if (!TextUtils.isEmpty(subTitle)) {
            ssb.append("\n");
            SpannableString ss = new SpannableString(subTitle);
            if (subTitleColor != 0) {
                ss.setSpan(new ForegroundColorSpan(subTitleColor), 0, ss.length(), 33);
            }
            ss.setSpan(new RelativeSizeSpan(0.78f), 0, ss.length(), 33);
            ssb.append(ss);
        }
        return ssb;
    }

    public static String capitalizeFirstLetter(String original) {
        if (original.length() == 0)
            return original;
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    public static String formatWithIata(String placeName, String iata) {
        placeName += CmTextUtil.OPEN_BRACKET + iata + CmTextUtil.CLOSE_BRACKET;
        return placeName;
    }
}
