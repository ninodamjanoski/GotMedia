package com.mobgen.gotmedia.core.listener;

import android.view.View;
import android.view.View.OnClickListener;

import com.mobgen.gotmedia.core.utilities.TimedGate;


public abstract class TimedDebouncingOnClickListener implements OnClickListener {
    private TimedGate mTimedGate = new TimedGate(getMinDelay());

    public abstract void doClick(View view);

    protected long getMinDelay() {
        return 1000;
    }

    public final void onClick(View v) {
        if (this.mTimedGate.canEnter()) {
            doClick(v);
        }
    }
}
