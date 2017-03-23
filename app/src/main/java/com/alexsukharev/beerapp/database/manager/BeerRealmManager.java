package com.alexsukharev.beerapp.database.manager;

import android.support.annotation.NonNull;

import com.alexsukharev.beerapp.database.OnChangeListener;
import com.alexsukharev.beerapp.database.model.Beer;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Subscription;

/**
 * Realm instance can only be accessed in the same thread where it was created and should be closed when
 * it is no longer needed.
 * Calling getRealm() for the first time will initialize Realm in the current thread.
 * Users of this class are responsible for calling close() when the work is done.
 * <p>
 * https://realm.io/docs/java/latest/#working-with-android
 * https://realm.io/docs/java/latest/#controlling-the-lifecycle-of-realm-instances
 */
public class BeerRealmManager implements IBeerDatabaseManager {

    private Realm mRealm;

    @Override
    public void storeBeers(@NonNull final List<Beer> beerList) {
        getRealm().executeTransactionAsync(realm -> realm.copyToRealmOrUpdate(beerList));
    }

    @Override
    public void storeBeer(@NonNull final Beer beer) {
        getRealm().executeTransactionAsync(realm -> realm.copyToRealmOrUpdate(beer));
    }

    @Override
    public Subscription getBeers(@NonNull final OnChangeListener<RealmResults<Beer>> onChangeListener) {
        return getRealm().where(Beer.class)
                .findAllAsync()
                .asObservable()
                .filter(RealmResults::isLoaded)
                .subscribe(onChangeListener::onChange);
    }

    @Override
    public Subscription getBeerById(final long id, @NonNull final OnChangeListener<Beer> onChangeListener) {
        return getRealm().where(Beer.class)
                .equalTo("id", id)
                .findFirstAsync()
                .asObservable()
                .filter(Beer::isLoaded)
                .subscribe(realmObject -> onChangeListener.onChange((Beer) realmObject));
    }

    @Override
    public void close() {
        if (mRealm != null) {
            mRealm.close();
        }
        mRealm = null;
    }

    private Realm getRealm() {
        if (mRealm == null) {
            mRealm = Realm.getDefaultInstance();
        }
        return mRealm;
    }

}