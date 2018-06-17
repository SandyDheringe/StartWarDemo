package com.arrk.starwardemo.repository.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.support.annotation.NonNull;

import com.arrk.starwardemo.network.SWAPIService;
import com.arrk.starwardemo.model.CharacterEntity;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author Sandeep.dheringe
 */

/**
 * A simple data source factory which also provides a way to observe the last created data source.
 * This allows us to channel its network request status etc back to the UI. See the Listing creation
 * in the Repository class.
 */
public class CharactersDataSourceFactory implements DataSource.Factory<Integer, CharacterEntity>
{

    @NonNull
    private final SWAPIService githubService;

    @NonNull
    private final CompositeDisposable compositeDisposable;

    @NonNull
    private final MutableLiveData<CharactersDataSource> usersDataSourceLiveData = new MutableLiveData<>();

    public CharactersDataSourceFactory(@NonNull SWAPIService githubService,
                                       @NonNull CompositeDisposable compositeDisposable)
    {
        this.githubService = githubService;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public DataSource<Integer, CharacterEntity> create()
    {
        CharactersDataSource usersDataSource = new CharactersDataSource(githubService, compositeDisposable);
        usersDataSourceLiveData.postValue(usersDataSource);
        return usersDataSource;
    }

    @NonNull
    public MutableLiveData<CharactersDataSource> getUsersDataSourceLiveData()
    {
        return usersDataSourceLiveData;
    }

}
