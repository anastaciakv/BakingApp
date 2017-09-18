package de.proximity.bakeme.ui.recipedetails;


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import javax.inject.Inject;

import de.proximity.bakeme.items.Recipe;

public class RecipeDetailsViewModel {

    public ObservableField<Recipe> recipe;

    public ObservableBoolean showIngredients = new ObservableBoolean(true);

    public void setShowIngredients(boolean show) {
        this.showIngredients.set(show);
    }

    @Inject
    public RecipeDetailsViewModel() {
        recipe = new ObservableField<>();
    }

    public void setRecipe(Recipe recipe) {
        this.recipe.set(recipe);
    }

    public String getIngredients() {
        return recipe.get().getIngredientsAsString();
    }
}
