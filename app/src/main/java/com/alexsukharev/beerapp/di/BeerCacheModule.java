package com.alexsukharev.beerapp.di;

import com.alexsukharev.beerapp.cache.manager.BeerCacheManager;
import com.alexsukharev.beerapp.cache.manager.IBeerCacheManager;

import dagger.Module;
import dagger.Provides;

@Module
public class BeerCacheModule {

    @Provides
    IBeerCacheManager providesBeerCacheManager() {
        return new BeerCacheManager();
    }
}