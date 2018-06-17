package com.arrk.starwardemo.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arrk.starwardemo.R;
import com.arrk.starwardemo.adapter.listener.SWCharacterClickHandler;
import com.arrk.starwardemo.databinding.ItemCharacterBinding;
import com.arrk.starwardemo.model.CharacterEntity;

/**
 * view holder.
 *
 * @author Sandeep.dheringe
 */
public class SWCharacterViewHolder extends RecyclerView.ViewHolder
{

    private final ItemCharacterBinding binding;

    private SWCharacterViewHolder(ItemCharacterBinding binding, SWCharacterClickHandler swCharacterClickHandler)
    {
        super(binding.getRoot());
        this.binding = binding;
        this.binding.setSwCharacterClickHandler(swCharacterClickHandler);
    }

    public static SWCharacterViewHolder create(ViewGroup parent, SWCharacterClickHandler swCharacterClickHandler)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCharacterBinding itemCharacterBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_character, parent, false);
        return new SWCharacterViewHolder(itemCharacterBinding, swCharacterClickHandler);
    }

    public void bindTo(CharacterEntity characterEntity)
    {
        binding.setCharacter(characterEntity);
        binding.executePendingBindings();
    }


}

