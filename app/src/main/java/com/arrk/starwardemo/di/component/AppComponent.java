package com.arrk.starwardemo.di.component;

import com.arrk.starwardemo.di.module.ActivityBindingModule;
import com.arrk.starwardemo.di.module.AppModule;
import com.arrk.starwardemo.di.module.ViewModelModule;
import com.arrk.starwardemo.di.scops.PerApplication;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author Sandeep.dheringe
 */
@PerApplication
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        ViewModelModule.class,
        AppModule.class})
interface AppComponent extends AndroidInjector<DaggerApplication>
{

}
