package com.arrk.starwardemo.adapter.listener;

import android.content.Intent;
import android.view.View;

import com.arrk.starwardemo.model.CharacterEntity;
import com.arrk.starwardemo.view.SWCharacterDetailsActivity;

import javax.inject.Inject;

/**
 *
 * character item click handler
 *
 *@author Sandeep.dheringe
 */
public class SWCharacterClickHandler
{

    @Inject
    public SWCharacterClickHandler()
    {
    }

    public void onCharacterClicked(View view, CharacterEntity characterEntity)
    {
        Intent detailActivityIntent = new Intent(view.getContext(), SWCharacterDetailsActivity.class);
        detailActivityIntent.putExtra(SWCharacterDetailsActivity.strCharacterDetails, characterEntity);
        view.getContext().startActivity(detailActivityIntent);
    }

}
