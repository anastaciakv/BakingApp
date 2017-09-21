package de.proximity.bakeme.items;


import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Recipe {
    public int id;
    public String name;
    public List<Ingredient> ingredients;
    public List<Step> steps;
    public int servings;
    public String image;

    public Recipe(String name) {
        this.name = name;
    }

    public Recipe() {

    }

    public String getIngredientsAsString() {
        if (ingredients == null) return "";
        StringBuilder builder = new StringBuilder();
        for (Ingredient i : ingredients) {
            builder.append(i.getQuantity()).append(" ").append(i.measure).append(" ").append(i.ingredient).append("\n");
        }
        int last = builder.lastIndexOf("\n");
        if (last >= 0) {
            builder.delete(last, builder.length());
        }
        return builder.toString();
    }
}
