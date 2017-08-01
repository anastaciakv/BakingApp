package de.proximity.bakeme.ui.recipelist;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class RecipeListViewModelTest {

    RecipeListViewModel model;

    @Before
    public void setUp() throws Exception {
        model = new RecipeListViewModel();
    }

    @Test
    public void when_created_then_isLoadingTrue() {
        assertTrue(model.isLoading.get());
    }

}