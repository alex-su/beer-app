package com.alexsukharev.beerapp.viewmodel;

import android.support.annotation.NonNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseViewModel {

    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public abstract void onStart();

    public void onStop() {
        mCompositeSubscription.clear();
    }

    public abstract void onDestroy();

    void addSubscription(@NonNull final Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }
}
