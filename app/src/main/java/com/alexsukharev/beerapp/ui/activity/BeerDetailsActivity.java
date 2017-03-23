package com.alexsukharev.beerapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.Toast;

import com.alexsukharev.beerapp.App;
import com.alexsukharev.beerapp.R;
import com.alexsukharev.beerapp.databinding.BeerDetailsActivityBinding;
import com.alexsukharev.beerapp.viewmodel.BaseViewModel;
import com.alexsukharev.beerapp.viewmodel.BeerDetailsViewModel;

public class BeerDetailsActivity extends BaseActivity {

    private static final String EXTRA_BEER_ID = "extra_beer_id";

    public static void start(@NonNull final Context context, final long beerId) {
        final Intent intent = new Intent(context, BeerDetailsActivity.class);
        intent.putExtra(EXTRA_BEER_ID, beerId);
        context.startActivity(intent);
    }

    private BeerDetailsActivityBinding mBinding;
    private BeerDetailsViewModel mViewModel;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.beer_details_activity);
        mViewModel = new BeerDetailsViewModel(((App) getApplicationContext()).getBeerComponent(), getIntent().getLongExtra(EXTRA_BEER_ID, -1));
        mBinding.setViewModel(mViewModel);
        initSupportActionBar();
        observeBeer();
        observeToastText();
    }

    private void initSupportActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void observeBeer() {
        addOnPropertyChangedCallback(mViewModel.beer, () -> mBinding.setBeer(mViewModel.beer.get()));
    }

    private void observeToastText() {
        addOnPropertyChangedCallback(mViewModel.toastText, () -> Toast.makeText(BeerDetailsActivity.this, mViewModel.toastText.get(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    protected BaseViewModel getViewModel() {
        return mViewModel;
    }
}
