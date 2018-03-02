package com.mobgen.gotmedia.core.utilities;

public class TimedGate {
    public static final long MIN_DELAY_MS = 1000;
    private long lastClickTime;
    private final long minDelayInMs;

    public TimedGate() {
        this(1000);
    }

    public TimedGate(long minDelayInMs) {
        this.minDelayInMs = minDelayInMs;
    }

    public boolean canEnter() {
        if (System.currentTimeMillis() - this.lastClickTime < this.minDelayInMs) {
            return false;
        }
        this.lastClickTime = System.currentTimeMillis();
        return true;
    }
}
