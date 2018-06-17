package com.arrk.starwardemo.repository;

import com.arrk.starwardemo.factory.BaseTest;
import com.arrk.starwardemo.model.CharacterEntity;
import com.arrk.starwardemo.model.ResultCharacter;
import com.arrk.starwardemo.network.SWAPIService;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import io.reactivex.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Sandeep.dheringe
 */

public class CharactersRepositoryImplTest extends BaseTest
{

    @Mock
    private SWAPIService githubService;

    @Mock
    private Throwable throwable;

    @InjectMocks
    private CharactersRepositoryImpl usersRepositoryImpl;

    @Test
    public void fetchUsersEmitsErrorWhenNetworkServiceErrors()
    {
        when(githubService.getStartWarCharacters(1)).thenReturn(Single.error(throwable));
    }

    @Test
    public void usersRawItemsFromServiceAreMapped() throws Exception
    {
        ResultCharacter userRawList = createUserRawList();
        when(githubService.getStartWarCharacters(1)).thenReturn(Single.just(userRawList));
    }

    private static ResultCharacter createUserRawList()
    {

        ResultCharacter resultCharacter = new ResultCharacter();

        resultCharacter.results = new ArrayList<CharacterEntity>()
        {
            {
                add(mock(CharacterEntity.class));
                add(mock(CharacterEntity.class));
                add(mock(CharacterEntity.class));
            }

        };

        return resultCharacter;
    }

}
