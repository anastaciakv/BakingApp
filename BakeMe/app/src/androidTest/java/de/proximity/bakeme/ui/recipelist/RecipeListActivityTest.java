package de.proximity.bakeme.ui.recipelist;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.proxiity.bakeme.data.MockRecipeTask;
import de.proximity.bakeme.R;
import de.proximity.bakeme.di.Injectable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecipeListActivityTest implements Injectable {
    @Rule
    public ActivityTestRule<RecipeListActivity> activityTestRule = new ActivityTestRule<>(RecipeListActivity.class);
    MockRecipeTask task;

    @Before
    public void setUp() throws Exception {
        task = new MockRecipeTask();
    }

    @Test
    public void recipeListIsFilledWithData() throws Exception {
        onView(withId(R.id.rvRecipeList))
                .check(matches(hasDescendant(withText(task.getRecipes().get(0).name))));
        onView(withId(R.id.rvRecipeList))
                .check(matches(hasDescendant(withText(task.getRecipes().get(1).name))));
    }

    @Test
    public void whenClickRecipe_thenOpenDetails() throws Exception {
        onView(withId(R.id.rvRecipeList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tvIngredients)).check(matches(withText(task.getRecipes().get(0).getIngredientsAsString())));
    }
}