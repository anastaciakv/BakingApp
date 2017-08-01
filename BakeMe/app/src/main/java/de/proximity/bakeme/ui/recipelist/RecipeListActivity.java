package de.proximity.bakeme.ui.recipelist;

import android.databinding.DataBindingUtil;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_list);
        binding.setViewModel(viewModel);
    }
}