package com.arrk.starwardemo.model;

import java.util.Date;

public class CharacterModelBuilder
{
    private String name;
    private String height;
    private String mass;
    private Date created;

    public CharacterModelBuilder setName(String name)
    {
        this.name = name;
        return this;
    }

    public CharacterModelBuilder setHeight(String height)
    {
        this.height = height;
        return this;
    }

    public CharacterModelBuilder setMass(String mass)
    {
        this.mass = mass;
        return this;
    }

    public CharacterModelBuilder setCreated(Date created)
    {
        this.created = created;
        return this;
    }


    public CharacterEntity build()
    {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.name = this.name;
        characterEntity.height = this.height;
        characterEntity.mass = this.mass;
        characterEntity.created = this.created;
        return characterEntity;
    }
}