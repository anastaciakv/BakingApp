package de.proximity.bakeme;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import de.proximity.bakeme.di.AppInjector;
import io.realm.Realm;
import timber.log.Timber;

public class MyApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> dispatchingAndroidFragmentInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
        AppInjector.init(this);
        Realm.init(this);
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidFragmentInjector;
    }
}
