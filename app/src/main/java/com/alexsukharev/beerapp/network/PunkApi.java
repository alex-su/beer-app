package com.alexsukharev.beerapp.network;

import com.alexsukharev.beerapp.model.Beer;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface PunkApi {

    @GET("beers")
    Observable<List<Beer>> getBeers();

    @GET("beers/{id}")
    Observable<List<Beer>> getBeer(@Path("id") long id);

}
