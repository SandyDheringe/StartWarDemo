package com.arrk.starwardemo.di.module;

import com.arrk.starwardemo.di.module.character.CharactersActivityModule;
import com.arrk.starwardemo.di.scops.PerActivity;
import com.arrk.starwardemo.view.SWCharacterListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Sandeep.dheringe
 */
@Module
public abstract class ActivityBindingModule
{

    @PerActivity
    @ContributesAndroidInjector(modules = CharactersActivityModule.class)
    abstract SWCharacterListActivity charactersActivity();

}
