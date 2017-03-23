package com.alexsukharev.beerapp.di;

import com.alexsukharev.beerapp.database.manager.BeerRealmManager;
import com.alexsukharev.beerapp.database.manager.IBeerDatabaseManager;

import dagger.Module;
import dagger.Provides;

@Module
public class BeerDatabaseModule {

    @Provides
    IBeerDatabaseManager providesBeerDatabaseManager() {
        return new BeerRealmManager();
    }
}