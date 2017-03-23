package com.alexsukharev.beerapp.database.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class IngredientList extends RealmObject {

    private RealmList<Ingredient> malt;

    private RealmList<Ingredient> hops;

    private String yeast;

    public RealmList<Ingredient> getMalt() {
        return malt;
    }

    public void setMalt(final RealmList<Ingredient> malt) {
        this.malt = malt;
    }

    public RealmList<Ingredient> getHops() {
        return hops;
    }

    public void setHops(final RealmList<Ingredient> hops) {
        this.hops = hops;
    }

    public String getYeast() {
        return yeast;
    }

    public void setYeast(final String yeast) {
        this.yeast = yeast;
    }
}
