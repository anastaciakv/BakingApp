package de.proximity.bakeme.data;


import java.util.List;

import de.proximity.bakeme.items.Recipe;

public interface RecipeTask {
    void fetchRecipes(Callback callback);

    interface Callback {

        void onRecipesFetched(List<Recipe> recipies);

        void onFailure();
    }
}
