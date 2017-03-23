package com.alexsukharev.beerapp.network.common;

import android.support.annotation.NonNull;

import com.alexsukharev.beerapp.BuildConfig;

import retrofit2.HttpException;
import rx.functions.Action1;

public class ErrorConsumer implements Action1<Throwable> {

    private ErrorCallback mErrorCallback;

    public ErrorConsumer(@NonNull final ErrorCallback errorCallback) {
        mErrorCallback = errorCallback;
    }

    @Override
    public void call(final Throwable e) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace();
        }
        mErrorCallback.onError(e instanceof HttpException ? ((HttpException) e).message() : e.getMessage());
    }

    public interface ErrorCallback {

        void onError(@NonNull String error);

    }

}
