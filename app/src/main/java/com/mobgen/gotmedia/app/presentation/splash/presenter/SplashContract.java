package com.mobgen.gotmedia.app.presentation.splash.presenter;

import com.mobgen.gotmedia.app.presentation.splash.SplashFragment;
import com.mobgen.gotmedia.core.base.BasePresenter;
import com.mobgen.gotmedia.core.base.BaseView;

/**
 * Created on 3/1/18.
 */

public class SplashContract {
    public interface SplashView extends BaseView<SplashPresenter> {

    }

    public interface SplashPresenter extends BasePresenter<SplashFragment> {

    }
}
