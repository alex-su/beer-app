package com.alexsukharev.beerapp.model;

import io.realm.RealmObject;

public class Ingredient extends RealmObject {

    private String name;

    private Amount amount;

    private String add;

    private String attribute;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(final Amount amount) {
        this.amount = amount;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(final String add) {
        this.add = add;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(final String attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return amount == null ? name : String.format("%s (%s %s)", name, amount.getValue(), amount.getUnit());
    }
}
