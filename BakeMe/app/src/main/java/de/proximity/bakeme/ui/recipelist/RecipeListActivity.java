package de.proximity.bakeme.ui.recipelist;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import de.proximity.bakeme.R;
import de.proximity.bakeme.databinding.ActivityRecipeListBinding;
import de.proximity.bakeme.di.Injectable;

public class RecipeListActivity extends AppCompatActivity implements Injectable {
    @Inject
    RecipeListViewModel viewModel;

    private ActivityRecipeListBinding binding;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_list);
        adapter = new RecipeAdapter();
        setupList();
        binding.setViewModel(viewModel);
        viewModel.recipesList.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                updateRecipeList();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRecipeList();
    }

    private void updateRecipeList() {
        adapter.update(viewModel.recipesList.get());
        adapter.notifyDataSetChanged();
    }

    private void setupList() {
        binding.rvRecipeList.setHasFixedSize(true);
        binding.rvRecipeList.setAdapter(adapter);
    }
}