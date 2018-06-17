package com.arrk.starwardemo.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.arrk.starwardemo.network.SWAPIService;
import com.arrk.starwardemo.model.CharacterEntity;
import com.arrk.starwardemo.repository.datasource.CharactersDataSource;
import com.arrk.starwardemo.repository.datasource.CharactersDataSourceFactory;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Repository implementation that returns a Listing that loads data directly from the network
 *
 * @author Sandeep.dheringe
 */
public class CharactersRepositoryImpl implements CharactersRepository
{

    private static final int pageSize = 10;
    @NonNull
    private final SWAPIService swapiService;
    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private CharactersDataSourceFactory charactersDataSourceFactory;

    @Inject
    CharactersRepositoryImpl(@NonNull SWAPIService swapiService)
    {
        this.swapiService = swapiService;
    }

    @Override
    public Listing<CharacterEntity> fetchUsers()
    {
        charactersDataSourceFactory = new CharactersDataSourceFactory(swapiService, compositeDisposable);

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize)
                .setEnablePlaceholders(false)
                .build();

        LiveData<PagedList<CharacterEntity>> pagedList = new LivePagedListBuilder<>(charactersDataSourceFactory, config).build();

        return new Listing<>(
                pagedList,
                Transformations.switchMap(charactersDataSourceFactory.getUsersDataSourceLiveData(), CharactersDataSource::getNetworkState),
                Transformations.switchMap(charactersDataSourceFactory.getUsersDataSourceLiveData(), CharactersDataSource::getInitialLoad)
        );
    }

    @Override
    public void retry()
    {
        charactersDataSourceFactory.getUsersDataSourceLiveData().getValue().retry();
    }

    @Override
    public void refresh()
    {
        charactersDataSourceFactory.getUsersDataSourceLiveData().getValue().invalidate();
    }

    @Override
    public void clear()
    {
        compositeDisposable.dispose();
    }

}
