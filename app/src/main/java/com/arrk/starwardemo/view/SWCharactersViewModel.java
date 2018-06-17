package com.arrk.starwardemo.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.arrk.starwardemo.model.CharacterEntity;
import com.arrk.starwardemo.repository.Listing;
import com.arrk.starwardemo.repository.NetworkState;
import com.arrk.starwardemo.repository.CharactersRepository;

import javax.inject.Inject;

/**
 * @author Sandeep.dheringe
 */

public class SWCharactersViewModel extends ViewModel
{

    @NonNull
    private final CharactersRepository charactersRepository;
    @NonNull
    public LiveData<PagedList<CharacterEntity>> characterList;
    @NonNull
    private Listing<CharacterEntity> characterListing;

    @Inject
    SWCharactersViewModel(@NonNull CharactersRepository charactersRepository)
    {
        this.charactersRepository = charactersRepository;
        characterListing = charactersRepository.fetchUsers();
        characterList = characterListing.getPagedList();
    }

    public void retry()
    {
        charactersRepository.retry();
    }

    public void refresh()
    {
        charactersRepository.refresh();
    }

    public LiveData<NetworkState> getNetworkState()
    {
        return characterListing.getNetworkState();
    }

    public LiveData<NetworkState> getRefreshState()
    {
        return characterListing.getRefreshState();
    }

    @Override
    protected void onCleared()
    {
        super.onCleared();
        charactersRepository.clear();
    }

}
