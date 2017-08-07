package de.proximity.bakeme.items;


import java.util.List;

public class Recipe {
    public int id;
    public String name;
    public List<Ingredient> ingredients = null;
    public List<Step> steps = null;
    public int servings;
    public String image;
}
