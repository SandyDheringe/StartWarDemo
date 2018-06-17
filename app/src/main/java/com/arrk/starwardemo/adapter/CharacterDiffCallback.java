package com.arrk.starwardemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.arrk.starwardemo.model.CharacterEntity;

import java.util.Objects;

import javax.inject.Inject;

/**
 *
 *
 * @author Sandeep.dheringe
 */
public class CharacterDiffCallback extends DiffUtil.ItemCallback<CharacterEntity>
{

    @Inject
    CharacterDiffCallback()
    {
    }

    public boolean areItemsTheSame(@NonNull CharacterEntity oldItem, @NonNull CharacterEntity newItem)
    {
        return oldItem.name.equalsIgnoreCase(newItem.name);
    }

    public boolean areContentsTheSame(@NonNull CharacterEntity oldItem, @NonNull CharacterEntity newItem)
    {
        return Objects.equals(oldItem, newItem);
    }

}

