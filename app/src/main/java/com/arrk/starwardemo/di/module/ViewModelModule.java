package com.arrk.starwardemo.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.arrk.starwardemo.factory.StarWarViewModelFactory;
import com.arrk.starwardemo.di.ViewModelKey;
import com.arrk.starwardemo.di.module.character.CharactersViewModelModule;
import com.arrk.starwardemo.view.SWCharactersViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * @author Sandeep.dheringe
 */
@Module(includes = CharactersViewModelModule.class)
public abstract class ViewModelModule
{

    @Binds
    @IntoMap
    @ViewModelKey(SWCharactersViewModel.class)
    abstract ViewModel bindUserViewModel(SWCharactersViewModel SWCharactersViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(StarWarViewModelFactory factory);

}
