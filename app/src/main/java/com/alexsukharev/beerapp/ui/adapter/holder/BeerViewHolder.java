package com.alexsukharev.beerapp.ui.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.alexsukharev.beerapp.databinding.ItemBeerBinding;

public class BeerViewHolder extends RecyclerView.ViewHolder {

    private ItemBeerBinding mBinding;

    public BeerViewHolder(@NonNull final ItemBeerBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public ItemBeerBinding getBinding() {
        return mBinding;
    }
}
