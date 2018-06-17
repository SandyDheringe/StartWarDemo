package com.arrk.starwardemo.model;

import com.arrk.starwardemo.model.validator.ModelValidator;

import org.junit.Test;

public class ModelValidatorTest
{

    @Test()
    public void shouldNotThrowErrorOnValidCharacterModel() throws IllegalArgumentException
    {
        CharacterModelBuilder builder = new CharacterModelBuilder();
        builder.setName("Sandeep");

        CharacterEntity characterEntity = builder.build();

        ModelValidator validator = new ModelValidator(characterEntity);
        validator.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorOnInvalidCharacterModel() throws IllegalArgumentException
    {
        CharacterModelBuilder builder = new CharacterModelBuilder();
        builder.setName(null);

        CharacterEntity characterEntity = builder.build();

        ModelValidator validator = new ModelValidator(characterEntity);
        validator.validate();
    }
}