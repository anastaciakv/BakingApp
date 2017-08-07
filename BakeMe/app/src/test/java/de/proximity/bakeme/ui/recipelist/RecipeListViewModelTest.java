package de.proximity.bakeme.ui.recipelist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import de.proximity.bakeme.data.RecipeTask;
import de.proximity.bakeme.items.Recipe;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class RecipeListViewModelTest {

    RecipeListViewModel model;
    @Mock
    RecipeTask task;
    @Captor
    ArgumentCaptor<RecipeTask.Callback> recipeTaskCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        model = new RecipeListViewModel(task);
    }

    @Test
    public void when_created_then_isLoadingTrue() {
        assertTrue(model.isLoading.get());
    }

    @Test
    public void when_created_then_fetchRecipes() {
        verify(task).fetchRecipes(model.getRecipeTaskCallback());
    }

    @Test
    public void recipeTaskCallbackIsNotNull() {
        assertNotNull(model.getRecipeTaskCallback());
    }

    @Test
    public void when_recipesFetched_then_hideLoader() {
        verify(task).fetchRecipes(recipeTaskCaptor.capture());
        recipeTaskCaptor.getValue().onRecipesFetched(new ArrayList<Recipe>());
        assertFalse(model.isLoading.get());
    }

    @Test
    public void when_recipesFetchFailed_then_hideLoader() {
        verify(task).fetchRecipes(recipeTaskCaptor.capture());
        recipeTaskCaptor.getValue().onFailure();
        assertFalse(model.isLoading.get());
    }

    @Test
    public void when_recipesFetched_then_updateList() {
        verify(task).fetchRecipes(recipeTaskCaptor.capture());
        List<Recipe> expectedRecipes = new ArrayList<>();
        recipeTaskCaptor.getValue().onRecipesFetched(expectedRecipes);
        assertEquals(expectedRecipes,model.recipesList.get());
    }

}