package de.proximity.bakeme.ui.recipedetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.parceler.Parcels;

import javax.inject.Inject;

import de.proximity.bakeme.R;
import de.proximity.bakeme.di.Injectable;
import de.proximity.bakeme.items.Recipe;
import de.proximity.bakeme.items.Step;
import de.proximity.bakeme.ui.recipedetails.step.StepFragment;

public class RecipeDetailsActivity extends AppCompatActivity implements Injectable, StepListAdapter.StepCallback {
    @Inject
    RecipeDetailsViewModel model;
    private Recipe recipe;
    private static final String EXTRA_RECIPE = "EXTRA_RECIPE";
    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(EXTRA_RECIPE)) {
            recipe = Parcels.unwrap(getIntent().getExtras().getParcelable(EXTRA_RECIPE));
            model.setRecipe(recipe);
        }
        if (findViewById(R.id.container) != null) {
            isTwoPane = true;
        } else {
            changeFragment(R.id.masterListContainer, new StepListFragment(), true, false);
        }
    }

    public static void start(Context context, Recipe recipe) {
        Intent intent = new Intent(context, RecipeDetailsActivity.class);
        intent.putExtra(EXTRA_RECIPE, Parcels.wrap(recipe));
        context.startActivity(intent);
    }

    @Override
    public void onStepClick(Step step) {
        if (isTwoPane) {
            changeFragment(R.id.container, StepFragment.newInstance(step), false, false);
        } else {
            changeFragment(R.id.masterListContainer, StepFragment.newInstance(step), false, true);
        }
    }

    private void changeFragment(int containerId, Fragment fragment, boolean add, boolean addToBackStack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tr = manager.beginTransaction();
        if (add) {
            tr.add(containerId, fragment);
        } else {
            tr.replace(containerId, fragment);
        }
        if (addToBackStack)
            tr.addToBackStack(null);
        tr.commit();
    }

}
