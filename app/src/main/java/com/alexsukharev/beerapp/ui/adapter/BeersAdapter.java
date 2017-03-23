package com.alexsukharev.beerapp.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alexsukharev.beerapp.R;
import com.alexsukharev.beerapp.database.model.Beer;
import com.alexsukharev.beerapp.databinding.ItemBeerBinding;
import com.alexsukharev.beerapp.navigator.BeerListNavigator;
import com.alexsukharev.beerapp.ui.adapter.holder.BeerViewHolder;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class BeersAdapter extends RealmRecyclerViewAdapter<Beer, BeerViewHolder> {

    private LayoutInflater mLayoutInflater;
    private BeerListNavigator mNavigator;

    public BeersAdapter(@NonNull final Context context, @Nullable final OrderedRealmCollection<Beer> data, @NonNull final BeerListNavigator navigator) {
        super(data, true);
        mLayoutInflater = LayoutInflater.from(context);
        mNavigator = navigator;
    }

    @Override
    public BeerViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final ItemBeerBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_beer, parent, false);
        binding.setNavigator(mNavigator);
        return new BeerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final BeerViewHolder holder, final int position) {
        final Beer beer = getItem(position);
        if (beer != null) {
            holder.getBinding().setBeer(beer);
        }
    }
}
