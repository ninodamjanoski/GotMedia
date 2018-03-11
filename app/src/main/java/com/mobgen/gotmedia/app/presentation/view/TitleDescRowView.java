package com.mobgen.gotmedia.app.presentation.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobgen.gotmedia.R;

/**
 * Created on 3/8/18.
 */

public class TitleDescRowView extends LinearLayout {
    private TextView textTitle;
    private TextView desc;
    private View divider;

    public TitleDescRowView(Context context) {
        super(context);
        init(null);
    }

    public TitleDescRowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TitleDescRowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_cell_title_desc_row, this, true);
        textTitle = view.findViewById(R.id.title_row);
        desc = view.findViewById(R.id.description);
        divider = view.findViewById(R.id.divider);
        if(attrs != null){
            TypedArray ta = getContext().obtainStyledAttributes(attrs, new int[]{android.R.attr.text, R.attr.hideDivider},
                    0, 0);
            CharSequence textTitle = null;
            boolean hideDivider;
            try {
                textTitle = ta.getText(0);
                hideDivider = ta.getBoolean(1, false);
            } finally {
                ta.recycle();
            }
            if(textTitle != null){
                this.textTitle.setText(textTitle);
            }
            if(hideDivider){
                divider.setVisibility(GONE);
            }
        }
    }

    public void hideDivider(boolean hide){
        if(hide){
            if(divider.getVisibility() != GONE)
            divider.setVisibility(GONE);
        }else {
            if(divider.getVisibility() != VISIBLE)
                divider.setVisibility(VISIBLE);
        }
    }

    public void setTextTitle(String textTitle) {
        this.textTitle.setText(textTitle);
    }

    public void setDesc(String textDesc) {
        this.desc.setText(textDesc);
    }

}