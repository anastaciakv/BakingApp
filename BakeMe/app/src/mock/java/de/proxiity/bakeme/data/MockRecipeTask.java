package de.proxiity.bakeme.data;


import java.util.ArrayList;
import java.util.List;

import de.proximity.bakeme.data.RecipeTask;
import de.proximity.bakeme.items.Recipe;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class MockRecipeTask implements RecipeTask {
    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Honeycomb"));
        recipes.add(new Recipe("Ice Cream Sandwich"));
        recipes.add(new Recipe("Jelly Bean"));
        recipes.add(new Recipe("Kitkat"));
        recipes.add(new Recipe("Lollipop"));
        recipes.add(new Recipe("Marshmallow"));
        recipes.add(new Recipe("Nougat"));
        recipes.add(new Recipe("Oreo"));
        return recipes;
    }

    @Override
    public void fetchRecipes(Callback callback) {
        checkNotNull(callback);
        callback.onRecipesFetched(getRecipes());
    }
}
