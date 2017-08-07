package de.proximity.bakeme.ui.recipelist;


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;

import javax.inject.Inject;

import de.proximity.bakeme.data.RecipeTask;
import de.proximity.bakeme.items.Recipe;

public class RecipeListViewModel {

    private final RecipeTask task;
    private RecipeTask.Callback callback;
    public ObservableBoolean isLoading = new ObservableBoolean(true);
    public ObservableField<List<Recipe>> recipesList = new ObservableField<>();

    @Inject
    public RecipeListViewModel(RecipeTask task) {
        this.task = task;
        fetchRecipes();
    }

    private void fetchRecipes() {
        task.fetchRecipes(getRecipeTaskCallback());
    }

    RecipeTask.Callback getRecipeTaskCallback() {
        if (callback == null) {
            callback = new RecipeTask.Callback() {
                @Override
                public void onRecipesFetched(List<Recipe> recipes) {
                    isLoading.set(false);
                    recipesList.set(recipes);
                }

                @Override
                public void onFailure() {
                    isLoading.set(false);
                }
            };
        }
        return callback;
    }
}
