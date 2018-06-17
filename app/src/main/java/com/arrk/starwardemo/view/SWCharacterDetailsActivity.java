package com.arrk.starwardemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arrk.starwardemo.R;
import com.arrk.starwardemo.databinding.ActivitySwcharacterDetailsBinding;
import com.arrk.starwardemo.model.CharacterEntity;

/**
 * Detail activity to show character details.
 *
 * @author Sandeep.dheringe
 */
public class SWCharacterDetailsActivity extends AppCompatActivity
{

    private ActivitySwcharacterDetailsBinding activitySwcharacterDetailsBinding;

    public static final String strCharacterDetails = "strCharacterDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activitySwcharacterDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_swcharacter_details);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            CharacterEntity characterEntity = bundle.getParcelable(strCharacterDetails);
            init(characterEntity);
        }
    }

    private void init(CharacterEntity characterEntity)
    {
        activitySwcharacterDetailsBinding.setCharacter(characterEntity);
    }

}