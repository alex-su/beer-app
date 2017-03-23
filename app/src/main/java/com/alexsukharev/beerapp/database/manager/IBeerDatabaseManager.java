package com.alexsukharev.beerapp.database.manager;

import android.support.annotation.NonNull;

import com.alexsukharev.beerapp.database.OnChangeListener;
import com.alexsukharev.beerapp.database.model.Beer;

import java.util.List;

import io.realm.RealmResults;
import rx.Subscription;

public interface IBeerDatabaseManager {

    void storeBeers(@NonNull List<Beer> beerList);

    void storeBeer(@NonNull Beer beer);

    Subscription getBeers(@NonNull OnChangeListener<RealmResults<Beer>> onChangeListener);

    Subscription getBeerById(long id, @NonNull OnChangeListener<Beer> onChangeListener);

    void close();

}
