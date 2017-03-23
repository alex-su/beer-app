package com.alexsukharev.beerapp.ui.activity;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.alexsukharev.beerapp.viewmodel.BaseViewModel;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        getViewModel().onStart();
    }

    @Override
    protected void onStop() {
        getViewModel().onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        getViewModel().onDestroy();
        super.onDestroy();
    }

    protected void addOnPropertyChangedCallback(@NonNull final BaseObservable observable, @NonNull final OnPropertyChangedCallback callback) {
        observable.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(final Observable observable, final int i) {
                callback.onPropertyChanged();
            }
        });
    }

    @NonNull
    abstract BaseViewModel getViewModel();

    interface OnPropertyChangedCallback {

        void onPropertyChanged();

    }

}
