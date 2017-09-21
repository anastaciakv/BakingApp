package de.proxiity.bakeme.data;


import java.util.ArrayList;
import java.util.List;

import de.proximity.bakeme.data.RecipeTask;
import de.proximity.bakeme.items.Ingredient;
import de.proximity.bakeme.items.Recipe;
import de.proximity.bakeme.items.Step;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class MockRecipeTask implements RecipeTask {
    private static List<Recipe> recipes;

    public static List<Recipe> getRecipes() {
        if (recipes == null) {
            recipes = new ArrayList<>();
            Recipe honeycomb = new Recipe("Honeycomb");
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(new Ingredient("honey", 200, "g"));
            ingredients.add(new Ingredient("egg", 2, ""));
            ingredients.add(new Ingredient("flour", 100, "g"));
            honeycomb.ingredients = ingredients;
            List<Step> steps = new ArrayList<>();
            steps.add(new Step(1, "Mix flour with egg", "Mix flour with egg in a bowl"));
            Step step2 = new Step(2, "Add honey", "");
            step2.videoURL = "https://www.youtube.com/watch?v=gayDlEYeaKw";
            steps.add(step2);

            steps.add(new Step(2, "Bake", "Preheat the oven to 200. Bake in a form for 20 minutes"));
            honeycomb.steps = steps;
            recipes.add(honeycomb);

            recipes.add(new Recipe("Ice Cream Sandwich"));
            recipes.add(new Recipe("Jelly Bean"));
            recipes.add(new Recipe("Kitkat"));
            recipes.add(new Recipe("Lollipop"));
            recipes.add(new Recipe("Marshmallow"));
            recipes.add(new Recipe("Nougat"));
            recipes.add(new Recipe("Oreo"));
        }
        return recipes;
    }

    @Override
    public void fetchRecipes(Callback callback) {
        checkNotNull(callback);
        callback.onRecipesFetched(getRecipes());
    }
}
