package de.proximity.bakeme.ui.recipedetails;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.parceler.Parcels;

import de.proxiity.bakeme.data.MockRecipeTask;
import de.proximity.bakeme.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static de.proximity.bakeme.ui.recipedetails.RecipeDetailsActivity.EXTRA_RECIPE;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailsActivityTest {
    @Rule
    public ActivityTestRule<RecipeDetailsActivity> activityTestRule = new ActivityTestRule<RecipeDetailsActivity>(RecipeDetailsActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent result = new Intent(targetContext, RecipeDetailsActivity.class);
            result.putExtra(EXTRA_RECIPE, Parcels.wrap(MockRecipeTask.getRecipes().get(0)));
            return result;
        }
    };

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void recipeIngredientsLoadedCorrectly() throws Exception {
        String expectedIngredients = MockRecipeTask.getRecipes().get(0).getIngredientsAsString();
        onView(withId(R.id.tvIngredients)).check(matches(withText(expectedIngredients)));
    }

    @Test
    public void recipeStepsLoadedCorrectly() throws Exception {
        String expectedStep1 = MockRecipeTask.getRecipes().get(0).steps.get(0).shortDescription;
        String expectedStep2 = MockRecipeTask.getRecipes().get(0).steps.get(1).shortDescription;
        String expectedStep3 = MockRecipeTask.getRecipes().get(0).steps.get(2).shortDescription;
        onView(withId(R.id.rvStepList)).check(matches(hasDescendant(withText(expectedStep1))));
        onView(withId(R.id.rvStepList)).check(matches(hasDescendant(withText(expectedStep2))));
        onView(withId(R.id.rvStepList)).check(matches(hasDescendant(withText(expectedStep3))));
    }

    @Test
    public void given_noVideoUrlForStep1_when_clickOnStep1_noPlayerShown() throws Exception {
        onView(withId(R.id.rvStepList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(allOf(instanceOf(SimpleExoPlayerView.class), withId(R.id.playerView))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @Test
    public void given_videoUrlForStep2_when_clickOnStep2_playerShown() throws Exception {
        onView(withId(R.id.rvStepList)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(allOf(instanceOf(SimpleExoPlayerView.class), withId(R.id.playerView))).check(matches(isDisplayed()));
    }

}