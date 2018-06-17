package com.arrk.starwardemo.di.module;

import com.arrk.starwardemo.BuildConfig;
import com.arrk.starwardemo.network.SWAPIService;
import com.arrk.starwardemo.network.SWAPIServiceFactory;
import com.arrk.starwardemo.di.scops.PerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sandeep.dheringe
 */
@Module
public class AppModule
{

    @Provides
    @PerApplication
    static SWAPIService provideSWAPIService()
    {
        return SWAPIServiceFactory.makeSWAPIService(BuildConfig.DEBUG);
    }

}
