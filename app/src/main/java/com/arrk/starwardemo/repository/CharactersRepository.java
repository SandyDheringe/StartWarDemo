package com.arrk.starwardemo.repository;

import com.arrk.starwardemo.model.CharacterEntity;

/**
 * @author Sandeep.dheringe
 */
public interface CharactersRepository
{

    Listing<CharacterEntity> fetchUsers();

    void retry();

    void refresh();

    void clear();

}
