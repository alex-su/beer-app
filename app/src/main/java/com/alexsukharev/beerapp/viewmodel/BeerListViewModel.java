package com.alexsukharev.beerapp.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.alexsukharev.beerapp.cache.manager.IBeerCacheManager;
import com.alexsukharev.beerapp.model.Beer;
import com.alexsukharev.beerapp.di.BeerComponent;
import com.alexsukharev.beerapp.network.manager.IBeerNetworkManager;

import javax.inject.Inject;

import io.realm.RealmResults;

@SuppressWarnings("WeakerAccess")
public class BeerListViewModel extends BaseViewModel {

    @Inject
    IBeerCacheManager mBeerDatabaseManager;

    @Inject
    IBeerNetworkManager mBeerNetworkManager;

    public ObservableBoolean networkProgressVisible = new ObservableBoolean(false);
    public ObservableField<String> toastText = new ObservableField<>();
    public ObservableField<RealmResults<Beer>> beerList = new ObservableField<>();

    private boolean mLoadedFromNetwork;

    public BeerListViewModel(@NonNull final BeerComponent beerComponent) {
        beerComponent.inject(this);
    }

    @Override
    public void onStart() {
        addSubscription(mBeerDatabaseManager.getBeers(beers -> beerList.set(beers)));
        if (!mLoadedFromNetwork) {
            loadBeersFromNetwork();
        }
    }

    @Override
    public void onDestroy() {
        mBeerDatabaseManager.close();
    }

    public void onRefresh() {
        loadBeersFromNetwork();
    }

    public void loadBeersFromNetwork() {
        networkProgressVisible.set(true);
        addSubscription(mBeerNetworkManager.loadBeers(() -> {
            networkProgressVisible.set(false);
            mLoadedFromNetwork = true;
        }, error -> {
            networkProgressVisible.set(false);
            toastText.set(error);
        }));
    }

}
