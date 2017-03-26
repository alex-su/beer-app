package com.alexsukharev.beerapp.cache.manager;

import android.support.annotation.NonNull;

import com.alexsukharev.beerapp.cache.OnChangeListener;
import com.alexsukharev.beerapp.model.Beer;

import java.util.List;

import io.realm.RealmResults;
import rx.Subscription;

public interface IBeerCacheManager {

    void storeBeers(@NonNull List<Beer> beerList);

    void storeBeer(@NonNull Beer beer);

    Subscription getBeers(@NonNull OnChangeListener<RealmResults<Beer>> onChangeListener);

    Subscription getBeerById(long id, @NonNull OnChangeListener<Beer> onChangeListener);

    void close();

}
