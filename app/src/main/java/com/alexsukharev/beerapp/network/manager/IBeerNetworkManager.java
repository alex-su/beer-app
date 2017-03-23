package com.alexsukharev.beerapp.network.manager;

import android.support.annotation.NonNull;

import com.alexsukharev.beerapp.network.common.ErrorConsumer;
import com.alexsukharev.beerapp.network.common.ResultConsumer;

import rx.Subscription;

public interface IBeerNetworkManager {

    Subscription loadBeers(@NonNull ResultConsumer.ResultCallback resultCallback, @NonNull ErrorConsumer.ErrorCallback errorCallback);

    Subscription loadBeerDetails(long beerId, @NonNull ResultConsumer.ResultCallback resultCallback, @NonNull ErrorConsumer.ErrorCallback errorCallback);


}
