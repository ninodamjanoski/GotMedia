package com.mobgen.gotmedia.core.utilities.rx;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SchedulerProviderImpl implements SchedulerProvider {
    public Scheduler getIo() {
        return Schedulers.io();
    }

    public Scheduler getMain() {
        return AndroidSchedulers.mainThread();
    }

    public Scheduler getComputation() {
        return Schedulers.computation();
    }
}
