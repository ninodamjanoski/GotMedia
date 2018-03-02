package com.mobgen.gotmedia.core.utilities.rx;

import rx.Scheduler;
import rx.schedulers.Schedulers;

public class ImmediateSchedulerProvider implements SchedulerProvider {
    @Override
    public Scheduler getComputation() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler getIo() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler getMain() {
        return Schedulers.immediate();
    }
}
