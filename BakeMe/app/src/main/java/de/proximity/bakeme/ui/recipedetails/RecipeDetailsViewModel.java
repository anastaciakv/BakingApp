package de.proximity.bakeme.ui.recipedetails;


import android.databinding.ObservableField;

import javax.inject.Inject;

import de.proximity.bakeme.items.Ingredient;
import de.proximity.bakeme.items.Recipe;

public class RecipeDetailsViewModel {

    public ObservableField<Recipe> recipe;

    @Inject
    public RecipeDetailsViewModel() {
        recipe = new ObservableField<>();
    }

    public void setRecipe(Recipe recipe) {
        this.recipe.set(recipe);
    }

    public String getIngredients() {
        StringBuilder builder = new StringBuilder();
        for (Ingredient i : recipe.get().ingredients) {
            builder.append(i.getQuantity()).append(" ").append(i.measure).append(" ").append(i.ingredient).append("\n");
        }
        int last = builder.lastIndexOf("\n");
        if (last >= 0) {
            builder.delete(last, builder.length());
        }
        return builder.toString();
    }
}
