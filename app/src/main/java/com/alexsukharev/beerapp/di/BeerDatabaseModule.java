package com.alexsukharev.beerapp.di;

import com.alexsukharev.beerapp.cache.manager.BeerCacheManager;
import com.alexsukharev.beerapp.cache.manager.IBeerCacheManager;

import dagger.Module;
import dagger.Provides;

@Module
public class BeerDatabaseModule {

    @Provides
    IBeerCacheManager providesBeerDatabaseManager() {
        return new BeerCacheManager();
    }
}