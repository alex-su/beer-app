package com.alexsukharev.beerapp.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.alexsukharev.beerapp.R;
import com.alexsukharev.beerapp.database.manager.IBeerDatabaseManager;
import com.alexsukharev.beerapp.database.model.Beer;
import com.alexsukharev.beerapp.database.model.Ingredient;
import com.alexsukharev.beerapp.di.BeerComponent;
import com.alexsukharev.beerapp.network.manager.IBeerNetworkManager;

import javax.inject.Inject;

import io.realm.RealmList;

public class BeerDetailsViewModel extends BaseViewModel {

    @Inject
    Application mApplication;

    @Inject
    IBeerDatabaseManager mBeerDatabaseManager;

    @Inject
    IBeerNetworkManager mBeerNetworkManager;

    private boolean mLoadedFromNetwork;

    private final long mBeerId;

    public ObservableField<Beer> beer = new ObservableField<>();
    public ObservableField<String> toastText = new ObservableField<>();
    public ObservableField<String> hopsText = new ObservableField<>();
    public ObservableField<String> maltText = new ObservableField<>();
    public ObservableField<String> yeastText = new ObservableField<>();

    public BeerDetailsViewModel(@NonNull final BeerComponent beerComponent, final long beerId) {
        beerComponent.inject(this);
        mBeerId = beerId;
    }

    @Override
    public void onStart() {
        addSubscription(mBeerDatabaseManager.getBeerById(mBeerId, (result) -> {
            beer.set(result);
            setIngredients(result);
        }));
        if (!mLoadedFromNetwork) {
            loadBeerDetailsFromNetwork();
        }
    }

    private void setIngredients(@NonNull final Beer beer) {
        if (beer.getIngredients() != null) {
            if (beer.getIngredients().getHops() != null) {
                hopsText.set(mApplication.getString(R.string.hops, ingredientListToString(beer.getIngredients().getHops())));
            }
            if (beer.getIngredients().getMalt() != null) {
                maltText.set(mApplication.getString(R.string.malt, ingredientListToString(beer.getIngredients().getMalt())));
            }
            if (beer.getIngredients().getYeast() != null) {
                yeastText.set(mApplication.getString(R.string.yeast, beer.getIngredients().getYeast()));
            }
        }
    }

    private String ingredientListToString(@NonNull final RealmList<Ingredient> ingredients) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final Ingredient ingredient : ingredients) {
            stringBuilder.append(ingredient).append(", ");
        }
        final String s = stringBuilder.toString();
        return s.length() > 0 ? s.substring(0, s.length() - 2) : s;
    }

    @Override
    public void onDestroy() {
        mBeerDatabaseManager.close();
    }

    private void loadBeerDetailsFromNetwork() {
        addSubscription(mBeerNetworkManager.loadBeerDetails(mBeerId, () -> mLoadedFromNetwork = true, (error) -> toastText.set(error)));
    }

}
