package com.alexsukharev.beerapp;

import android.app.Application;

import com.alexsukharev.beerapp.di.AppModule;
import com.alexsukharev.beerapp.di.BeerComponent;
import com.alexsukharev.beerapp.di.BeerDatabaseModule;
import com.alexsukharev.beerapp.di.BeerNetworkModule;
import com.alexsukharev.beerapp.di.DaggerBeerComponent;
import com.alexsukharev.beerapp.di.NetworkModule;

import io.realm.Realm;

public class App extends Application {

    private BeerComponent mBeerComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        mBeerComponent = DaggerBeerComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .beerDatabaseModule(new BeerDatabaseModule())
                .beerNetworkModule(new BeerNetworkModule())
                .build();
    }

    public BeerComponent getBeerComponent() {
        return mBeerComponent;
    }

}
