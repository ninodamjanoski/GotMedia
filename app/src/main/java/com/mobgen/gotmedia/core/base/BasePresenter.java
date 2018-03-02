package com.mobgen.gotmedia.core.base;

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();

}
