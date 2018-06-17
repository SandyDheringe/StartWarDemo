package com.arrk.starwardemo.adapter.databinding;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Binding adapter to handle action.
 *
 * @author sandeep.dheringe
 */
public final class CharacterBindingAdapter
{

    private CharacterBindingAdapter() {
    }

    @BindingAdapter("visibility")
    public static void setVisibility(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

}
