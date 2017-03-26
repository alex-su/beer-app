package com.alexsukharev.beerapp.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.alexsukharev.beerapp.App;
import com.alexsukharev.beerapp.R;
import com.alexsukharev.beerapp.databinding.MainActivityBinding;
import com.alexsukharev.beerapp.navigator.BeerListNavigator;
import com.alexsukharev.beerapp.ui.adapter.BeersAdapter;
import com.alexsukharev.beerapp.viewmodel.BaseViewModel;
import com.alexsukharev.beerapp.viewmodel.BeerListViewModel;

public class BeerListActivity extends BaseActivity implements BeerListNavigator {

    private MainActivityBinding mBinding;
    private BeersAdapter mBeersAdapter;
    private BeerListViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        mViewModel = new BeerListViewModel(((App) getApplicationContext()).getBeerComponent());
        mBinding.setViewModel(mViewModel);
        initRecyclerView();
        subscribeToBeerList();
        subscribeToToastText();
    }

    private void initRecyclerView() {
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setItemAnimator(null);
    }

    private void subscribeToBeerList() {
        addOnPropertyChangedCallback(mViewModel.beerList, () -> {
            // mViewModel.beerList.get() returns a RealmResults object which updates automatically,
            // so we need to pass it to the adapter only once
            if (mBeersAdapter == null) {
                mBeersAdapter = new BeersAdapter(this, mViewModel.beerList.get(), this);
                mBinding.recyclerView.setAdapter(mBeersAdapter);
            }
        });
    }

    private void subscribeToToastText() {
        addOnPropertyChangedCallback(mViewModel.toastText, () -> Toast.makeText(this, mViewModel.toastText.get(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public void goToBeerDetails(final long beerId) {
        BeerDetailsActivity.start(this, beerId);
    }

    @NonNull
    @Override
    protected BaseViewModel getViewModel() {
        return mViewModel;
    }
}
