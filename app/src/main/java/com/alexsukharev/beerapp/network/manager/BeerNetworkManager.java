package com.alexsukharev.beerapp.network.manager;

import android.support.annotation.NonNull;

import com.alexsukharev.beerapp.cache.manager.IBeerCacheManager;
import com.alexsukharev.beerapp.model.Beer;
import com.alexsukharev.beerapp.network.PunkApi;
import com.alexsukharev.beerapp.network.common.ErrorConsumer;
import com.alexsukharev.beerapp.network.common.ResultConsumer;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class BeerNetworkManager implements IBeerNetworkManager {

    private final PunkApi mPunkApi;
    private final IBeerCacheManager mBeerDatabaseManager;

    public BeerNetworkManager(@NonNull final PunkApi punkApi, @NonNull final IBeerCacheManager beerDatabaseManager) {
        mPunkApi = punkApi;
        mBeerDatabaseManager = beerDatabaseManager;
    }

    @Override
    public Subscription loadBeers(@NonNull final ResultConsumer.ResultCallback resultCallback, @NonNull final ErrorConsumer.ErrorCallback errorCallback) {
        return mPunkApi.getBeers()
                .doOnNext(this::storeBeers)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResultConsumer<>(resultCallback), new ErrorConsumer(errorCallback));
    }

    @Override
    public Subscription loadBeerDetails(final long beerId, @NonNull final ResultConsumer.ResultCallback resultCallback, @NonNull final ErrorConsumer.ErrorCallback errorCallback) {
        // For some reason the API returns an array of Beers containing 1 item
        return mPunkApi.getBeer(beerId)
                .flatMap(beers -> Observable.just(beers.get(0)))
                .doOnNext(this::storeBeer)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResultConsumer<>(resultCallback), new ErrorConsumer(errorCallback));
    }

    private void storeBeers(@NonNull final List<Beer> beerList) {
        mBeerDatabaseManager.storeBeers(beerList);
        mBeerDatabaseManager.close();
    }

    private void storeBeer(@NonNull final Beer beer) {
        mBeerDatabaseManager.storeBeer(beer);
        mBeerDatabaseManager.close();
    }

}
