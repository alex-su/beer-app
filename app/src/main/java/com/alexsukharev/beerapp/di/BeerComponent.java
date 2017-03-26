package com.alexsukharev.beerapp.di;

import com.alexsukharev.beerapp.viewmodel.BeerDetailsViewModel;
import com.alexsukharev.beerapp.viewmodel.BeerListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, BeerNetworkModule.class, BeerCacheModule.class})
public interface BeerComponent {
    void inject(BeerListViewModel viewModel);
    void inject(BeerDetailsViewModel viewModel);
}