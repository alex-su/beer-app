package com.alexsukharev.beerapp.di;

import com.alexsukharev.beerapp.network.PunkApi;
import com.alexsukharev.beerapp.network.manager.BeerNetworkManager;
import com.alexsukharev.beerapp.network.manager.IBeerNetworkManager;
import com.alexsukharev.beerapp.database.manager.IBeerDatabaseManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BeerNetworkModule {

    @Provides
    @Singleton
    IBeerNetworkManager providesBeerNetworkManager(final PunkApi punkApi, final IBeerDatabaseManager beerDatabaseManager) {
        return new BeerNetworkManager(punkApi, beerDatabaseManager);
    }
}