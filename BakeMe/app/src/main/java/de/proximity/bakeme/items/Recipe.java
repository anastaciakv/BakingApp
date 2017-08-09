package de.proximity.bakeme.items;


import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Recipe {
    public int id;
    public String name;
    public List<Ingredient> ingredients = null;
    public List<Step> steps = null;
    public int servings;
    public String image;
}
