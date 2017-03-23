package com.alexsukharev.beerapp.database.model;

import io.realm.RealmObject;

public class Amount extends RealmObject {

    private float value;

    private String unit;

    public float getValue() {
        return value;
    }

    public void setValue(final float value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(final String unit) {
        this.unit = unit;
    }
}
