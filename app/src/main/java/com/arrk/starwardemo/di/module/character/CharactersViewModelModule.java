package com.arrk.starwardemo.di.module.character;

import com.arrk.starwardemo.repository.CharactersRepository;
import com.arrk.starwardemo.repository.CharactersRepositoryImpl;

import dagger.Binds;
import dagger.Module;

/**
 * @author Sandeep.dheringe
 */
@Module
public abstract class CharactersViewModelModule
{

    @Binds
    public abstract CharactersRepository bindUsersRepository(CharactersRepositoryImpl usersRepository);

}
