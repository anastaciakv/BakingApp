package de.proximity.bakeme.data;


import android.support.annotation.NonNull;

import java.util.List;

import de.proximity.bakeme.items.Recipe;
import retrofit2.Call;
import retrofit2.Response;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


public class RecipeTaskRetrofit implements RecipeTask {

    private final ApiClient client;

    public RecipeTaskRetrofit(ApiClient client) {
        this.client = client;
    }

    @Override
    public void fetchRecipes(@NonNull final Callback callback) {
        checkNotNull(callback);
        Call<List<Recipe>> call = client.getAllRecipes();
        call.enqueue(new retrofit2.Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                callback.onRecipesFetched(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
