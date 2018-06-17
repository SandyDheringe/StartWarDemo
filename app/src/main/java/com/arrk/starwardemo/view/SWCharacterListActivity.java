package com.arrk.starwardemo.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.arrk.starwardemo.R;
import com.arrk.starwardemo.adapter.CharacterAdapter;
import com.arrk.starwardemo.databinding.ActivityCharactersBinding;
import com.arrk.starwardemo.repository.NetworkState;
import com.arrk.starwardemo.repository.Status;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public class SWCharacterListActivity extends DaggerAppCompatActivity
{

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    CharacterAdapter characterAdapter;

    private SWCharactersViewModel SWCharactersViewModel;

    private ActivityCharactersBinding activityCharactersBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityCharactersBinding = DataBindingUtil.setContentView(this, R.layout.activity_characters);
        SWCharactersViewModel = ViewModelProviders.of(this, viewModelFactory).get(SWCharactersViewModel.class);
        initAdapter();
        initSwipeToRefresh();
    }

    private void initAdapter()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        activityCharactersBinding.charactersRecyclerView.setLayoutManager(linearLayoutManager);
        activityCharactersBinding.charactersRecyclerView.setAdapter(characterAdapter);
        SWCharactersViewModel.characterList.observe(this, characterAdapter::submitList);

        SWCharactersViewModel.getNetworkState().observe(this, characterAdapter::setNetworkState);
        characterAdapter.setRetryCallback(() -> SWCharactersViewModel.retry());
    }

    /**
     * Init swipe to refresh and enable pull to refresh only when there are items in the adapter
     */
    private void initSwipeToRefresh()
    {
        SWCharactersViewModel.getRefreshState().observe(this, networkState -> {
            if (characterAdapter.getCurrentList() != null && characterAdapter.getCurrentList().size() > 0)
            {
                activityCharactersBinding.charactersSwipeRefreshLayout.setRefreshing(
                        networkState.getStatus() == NetworkState.LOADING.getStatus());
            } else
            {
                setInitialLoadingState(networkState);
            }
        });
        activityCharactersBinding.charactersSwipeRefreshLayout.setOnRefreshListener(() -> SWCharactersViewModel.refresh());
    }

    /**
     * Show the current network state for the first load when the user list
     * in the adapter is empty and disable swipe to scroll at the first loading
     *
     * @param networkState the new network state
     */
    private void setInitialLoadingState(NetworkState networkState)
    {
        activityCharactersBinding.setRetryCallback(() -> SWCharactersViewModel.retry());
        activityCharactersBinding.setNetworkState(networkState);
        if (networkState.getStatus() == Status.SUCCESS)
        {
            activityCharactersBinding.charactersSwipeRefreshLayout.setEnabled(true);
        } else
        {
            activityCharactersBinding.charactersSwipeRefreshLayout.setEnabled(false);
        }
    }

}
