package com.arrk.starwardemo.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.arrk.starwardemo.R;
import com.arrk.starwardemo.adapter.listener.RetryListener;
import com.arrk.starwardemo.adapter.listener.SWCharacterClickHandler;
import com.arrk.starwardemo.adapter.viewholder.NetworkStateViewHolder;
import com.arrk.starwardemo.adapter.viewholder.SWCharacterViewHolder;
import com.arrk.starwardemo.model.CharacterEntity;
import com.arrk.starwardemo.repository.NetworkState;

import javax.inject.Inject;

/**
 * Paging library adapter.
 *
 * @author Sandeep.dheringe
 */
public class CharacterAdapter extends PagedListAdapter<CharacterEntity, RecyclerView.ViewHolder>
{
    private SWCharacterClickHandler swCharacterClickHandler;

    private NetworkState networkState;

    private RetryListener retryCallback;

    @Inject
    public CharacterAdapter(CharacterDiffCallback characterDiffCallback, SWCharacterClickHandler swCharacterClickHandler)
    {
        super(characterDiffCallback);
        this.swCharacterClickHandler = swCharacterClickHandler;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        switch (viewType)
        {
            case R.layout.item_character:
                return SWCharacterViewHolder.create(parent, swCharacterClickHandler);
            case R.layout.item_network_state:
                return NetworkStateViewHolder.create(parent, retryCallback);
            default:
                throw new IllegalArgumentException("unknown view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        switch (getItemViewType(position))
        {
            case R.layout.item_character:
                ((SWCharacterViewHolder) holder).bindTo(getItem(position));
                break;
            case R.layout.item_network_state:
                ((NetworkStateViewHolder) holder).bindTo(networkState);
                break;
        }
    }

    private boolean hasExtraRow()
    {
        return networkState != null && networkState != NetworkState.LOADED;
    }

    @Override
    public int getItemViewType(int position)
    {
        if (hasExtraRow() && position == getItemCount() - 1)
        {
            return R.layout.item_network_state;
        } else
        {
            return R.layout.item_character;
        }
    }

    @Override
    public int getItemCount()
    {
        return super.getItemCount() + (hasExtraRow() ? 1 : 0);
    }

    /**
     * Set the current network state to the adapter
     * but this work only after the initial load
     * and the adapter already have list to add new loading raw to it
     * so the initial loading state the activity responsible for handle it
     *
     * @param newNetworkState the new network state
     */
    public void setNetworkState(NetworkState newNetworkState)
    {
        if (getCurrentList() != null)
        {
            if (getCurrentList().size() != 0)
            {
                NetworkState previousState = this.networkState;
                boolean hadExtraRow = hasExtraRow();
                this.networkState = newNetworkState;
                boolean hasExtraRow = hasExtraRow();
                if (hadExtraRow != hasExtraRow)
                {
                    if (hadExtraRow)
                    {
                        notifyItemRemoved(super.getItemCount());
                    } else
                    {
                        notifyItemInserted(super.getItemCount());
                    }
                } else if (hasExtraRow && previousState != newNetworkState)
                {
                    notifyItemChanged(getItemCount() - 1);
                }
            }
        }
    }

    public void setRetryCallback(RetryListener retryCallback)
    {
        this.retryCallback = retryCallback;
    }

}
