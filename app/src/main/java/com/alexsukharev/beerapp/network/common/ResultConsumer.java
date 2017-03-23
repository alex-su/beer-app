package com.alexsukharev.beerapp.network.common;

import android.support.annotation.NonNull;

import rx.functions.Action1;

public class ResultConsumer<E> implements Action1<E> {

    private ResultCallback mResultCallback;

    public ResultConsumer(@NonNull final ResultCallback resultCallback) {
        mResultCallback = resultCallback;
    }

    @Override
    public void call(final E e) {
        mResultCallback.onSuccess();
    }

    public interface ResultCallback {

        void onSuccess();

    }

}
