package com.mobgen.gotmedia.core.utilities.rx;

import rx.Scheduler;

public interface SchedulerProvider {
    Scheduler getComputation();

    Scheduler getIo();

    Scheduler getMain();
}
