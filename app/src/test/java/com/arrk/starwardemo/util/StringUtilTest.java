package com.arrk.starwardemo.util;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Sandeep.dheringe
 */
public class StringUtilTest
{

    @Test
    public void stringIsNull_shouldReturnEmptyString()
    {
        String string = null;
        string = StringUtil.notNullOrEmpty(string);
        assertThat(string).isEqualTo("");
    }

    @Test
    public void stringNotNull_shouldReturnTheSameValue()
    {
        String string = "Hello";
        string = StringUtil.notNullOrEmpty(string);
        assertThat(string).isEqualTo("Hello");
    }

}
