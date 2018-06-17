package com.arrk.starwardemo.util;

import android.support.annotation.Nullable;

/**
 * @author Sandeep.dheringe
 */

public class StringUtil
{

    /**
     * Helper method to check if a string not null
     * and if it's null return empty string
     *
     * @param string the string to check
     * @return the String after the check
     */
    public static String notNullOrEmpty(@Nullable String string)
    {
        if (string == null)
        {
            return "";
        }
        return string;
    }



}
