package de.proximity.bakeme.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import de.proximity.bakeme.ui.recipelist.RecipeListActivity;


@Module
public abstract class ScreenModule {
    @ContributesAndroidInjector
    abstract RecipeListActivity contributeRecipeListActivity();


}
