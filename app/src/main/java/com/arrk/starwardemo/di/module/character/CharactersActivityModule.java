package com.arrk.starwardemo.di.module.character;

import com.arrk.starwardemo.adapter.CharacterAdapter;
import com.arrk.starwardemo.adapter.CharacterDiffCallback;
import com.arrk.starwardemo.adapter.listener.SWCharacterClickHandler;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sandeep.dheringe
 */
@Module
public class CharactersActivityModule
{

    @Provides
    static CharacterAdapter provideUserAdapter(CharacterDiffCallback userDiffCallback, SWCharacterClickHandler userClickHandler)
    {
        return new CharacterAdapter(userDiffCallback, userClickHandler);
    }

}