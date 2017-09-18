package de.proximity.bakeme.widget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RemoteViews;

import javax.inject.Inject;

import de.proximity.bakeme.R;
import de.proximity.bakeme.databinding.ActivityWidgetConfigurationBinding;
import de.proximity.bakeme.di.Injectable;
import de.proximity.bakeme.items.Recipe;
import de.proximity.bakeme.ui.recipelist.RecipeAdapter;

public class WidgetConfigActivity extends AppCompatActivity implements Injectable, RecipeAdapter.RecipeClickCallback {

    private int mAppWidgetId;
    @Inject
    WidgetConfigViewModel viewModel;
    private ActivityWidgetConfigurationBinding binding;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_widget_configuration);
        adapter = new RecipeAdapter(this);
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
        viewModel.onResume();
    }

    private void updateRecipeList() {
        adapter.update(viewModel.recipesList.get());
        adapter.notifyDataSetChanged();
    }

    private void setupList() {
        binding.rvRecipeList.setHasFixedSize(true);
        binding.rvRecipeList.setAdapter(adapter);
    }

    private void updateWidget(Recipe recipe) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews views = new RemoteViews(getPackageName(),
                R.layout.bake_me_widget);
        views.setTextViewText(R.id.widget_recipe_ingredients, recipe.getIngredientsAsString());
        views.setTextViewText(R.id.widget_recipe_title, recipe.name);
        appWidgetManager.updateAppWidget(mAppWidgetId, views);
    }

    private void returnResult() {
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }

    @Override
    public void onClick(Recipe recipe) {
        updateWidget(recipe);
        returnResult();
    }
}
