package de.proximity.bakeme.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import de.proximity.bakeme.ui.recipedetails.RecipeDetailsActivity;
import de.proximity.bakeme.ui.recipedetails.StepListFragment;
import de.proximity.bakeme.ui.recipelist.RecipeListActivity;
import de.proximity.bakeme.widget.WidgetConfigActivity;


@Module
public abstract class ScreenModule {
    @ContributesAndroidInjector
    abstract RecipeListActivity contributeRecipeListActivity();

    @ContributesAndroidInjector
    abstract RecipeDetailsActivity contributeRecipeDetailsActivity();

    @ContributesAndroidInjector
    abstract WidgetConfigActivity contributeWidgetConfigActivity();

    @ContributesAndroidInjector
    abstract StepListFragment contributeStepListFragment();
}
