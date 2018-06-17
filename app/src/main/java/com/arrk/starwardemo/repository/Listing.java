package com.arrk.starwardemo.repository;


import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

/**
 * Data class that is necessary for a UI to show a listing and interact w/ the rest of the system
 * @author Sandeep.dheringe
 */
public class Listing<T>
{

    // the LiveData of paged lists for the UI to observe
    private LiveData<PagedList<T>> pagedList;

    // represents the network request status to show to the user
    private LiveData<NetworkState> networkState;

    // represents the refresh status to show to the user. Separate from networkState, this
    // value is importantly only when refresh is requested.
    private LiveData<NetworkState> refreshState;

    Listing(LiveData<PagedList<T>> pagedList, LiveData<NetworkState> networkState, LiveData<NetworkState> refreshState)
    {
        this.pagedList = pagedList;
        this.networkState = networkState;
        this.refreshState = refreshState;
    }

    public LiveData<PagedList<T>> getPagedList()
    {
        return pagedList;
    }

    public LiveData<NetworkState> getNetworkState()
    {
        return networkState;
    }

    public LiveData<NetworkState> getRefreshState()
    {
        return refreshState;
    }

}
