package com.arrk.starwardemo.repository.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import com.arrk.starwardemo.network.SWAPIService;
import com.arrk.starwardemo.model.CharacterEntity;
import com.arrk.starwardemo.repository.NetworkState;
import com.arrk.starwardemo.util.Constants;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * A data source that uses the "id" field of posts as the key for next/prev pages.
 */
public class CharactersDataSource extends ItemKeyedDataSource<Integer, CharacterEntity>
{
    private static int page;
    @NonNull
    private final SWAPIService githubService;
    @NonNull
    private final CompositeDisposable compositeDisposable;
    @NonNull
    private final MutableLiveData<NetworkState> networkState = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<NetworkState> initialLoad = new MutableLiveData<>();
    /**
     * Keep Completable reference for the retry event
     */
    private Completable retryCompletable;

    CharactersDataSource(@NonNull SWAPIService githubService,
                         @NonNull CompositeDisposable compositeDisposable)
    {
        this.githubService = githubService;
        this.compositeDisposable = compositeDisposable;
    }

    public void retry()
    {
        if (retryCompletable != null)
        {
            compositeDisposable.add(retryCompletable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                    }, throwable -> Timber.e(throwable.getMessage())));
        }
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<CharacterEntity> callback)
    {
        // update network states.
        // we also provide an initial load state to the listeners so that the UI can know when the
        // very first list is loaded.
        networkState.postValue(NetworkState.LOADING);
        initialLoad.postValue(NetworkState.LOADING);

        //get the initial users from the api
        compositeDisposable.add(githubService.getStartWarCharacters(1)
                .map(resultCharacter -> {
                    if (resultCharacter.next == null)
                        page = -1;
                    return resultCharacter.results;
                }).subscribe(characterEntities -> {
                            // clear retry since last request succeeded
                            setRetry(null);
                            networkState.postValue(NetworkState.LOADED);
                            initialLoad.postValue(NetworkState.LOADED);
                            callback.onResult(characterEntities);
                        },
                        throwable -> {
                            // keep a Completable for future retry
                            setRetry(() -> loadInitial(params, callback));
                            NetworkState error = NetworkState.error(Constants.ERROR_STRING);
                            // publish the error
                            networkState.postValue(error);
                            initialLoad.postValue(error);
                        }));
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<CharacterEntity> callback)
    {
        if (page == 0)
        {
            networkState.postValue(NetworkState.LOADED);
            return;
        }

        // set network value to loading.
        networkState.postValue(NetworkState.LOADING);

        //get the users from the api after id
        compositeDisposable.add(githubService.getStartWarCharacters(params.key)
                .map(resultCharacter -> {
                    if (resultCharacter.next == null)
                        page = -1;
                    return resultCharacter.results;
                })
                .subscribe(characterEntities -> {
                            // clear retry since last request succeeded
                            setRetry(null);
                            networkState.postValue(NetworkState.LOADED);
                            callback.onResult(characterEntities);
                        },
                        throwable -> {
                            // keep a Completable for future retry
                            setRetry(() -> loadAfter(params, callback));
                            // publish the error
                            networkState.postValue(NetworkState.error(Constants.ERROR_STRING));
                        }));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<CharacterEntity> callback)
    {
    }

    /**
     * The id field is a unique identifier for users.
     */
    @NonNull
    @Override
    public Integer getKey(@NonNull CharacterEntity characterEntity)
    {
        return ++page;
    }

    @NonNull
    public MutableLiveData<NetworkState> getNetworkState()
    {
        return networkState;
    }

    @NonNull
    public MutableLiveData<NetworkState> getInitialLoad()
    {
        return initialLoad;
    }

    private void setRetry(final Action action)
    {
        if (action == null)
        {
            this.retryCompletable = null;
        } else
        {
            this.retryCompletable = Completable.fromAction(action);
        }
    }

}
