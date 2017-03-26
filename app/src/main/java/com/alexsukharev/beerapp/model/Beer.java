package com.alexsukharev.beerapp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Beer extends RealmObject {

    @PrimaryKey
    private long id;

    private String name;

    private String description;

    private String imageUrl;

    private IngredientList ingredients;

    private float abv;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public IngredientList getIngredients() {
        return ingredients;
    }

    public void setIngredients(final IngredientList ingredients) {
        this.ingredients = ingredients;
    }

    public float getAbv() {
        return abv;
    }

    public void setAbv(final float abv) {
        this.abv = abv;
    }
}
