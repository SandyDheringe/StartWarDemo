package com.arrk.starwardemo.network;

import com.arrk.starwardemo.model.ResultCharacter;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Sandeep.Dheringe
 */
public interface SWAPIService
{
    /**
     * Get star war character details.
     *
     * @return list of the users raw data
     */
    @GET("people/")
    Single<ResultCharacter> getStartWarCharacters(@Query("page") int page);


}
