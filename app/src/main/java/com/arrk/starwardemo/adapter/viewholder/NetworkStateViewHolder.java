package com.arrk.starwardemo.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arrk.starwardemo.R;
import com.arrk.starwardemo.adapter.listener.RetryListener;
import com.arrk.starwardemo.databinding.ItemNetworkStateBinding;
import com.arrk.starwardemo.repository.NetworkState;

/**
 * Holds the network state
 *
 * @author Sandeep.dheringe
 */
public class NetworkStateViewHolder extends RecyclerView.ViewHolder
{

    private final ItemNetworkStateBinding binding;

    private final RetryListener retryCallback;

    private NetworkStateViewHolder(ItemNetworkStateBinding binding, RetryListener retryCallback)
    {
        super(binding.getRoot());
        this.binding = binding;
        this.retryCallback = retryCallback;
    }

    public static NetworkStateViewHolder create(ViewGroup parent, RetryListener retryCallback)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemNetworkStateBinding itemNetworkStateBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_network_state, parent, false);
        return new NetworkStateViewHolder(itemNetworkStateBinding, retryCallback);
    }

    public void bindTo(NetworkState networkState)
    {
        binding.setNetworkState(networkState);
        binding.setCallback(retryCallback);
        binding.executePendingBindings();
    }

}
