package com.rahmatsyah.moviecatalogue.ui;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.testing.SingleFragmentActivity;
import com.rahmatsyah.moviecatalogue.ui.tvshow.TvShowFragment;
import com.rahmatsyah.moviecatalogue.utils.EspressoIdlingResource;
import com.rahmatsyah.moviecatalogue.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class TvShowTest {

    private TvShowFragment tvShowFragment = new TvShowFragment();

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityTestRule = new ActivityTestRule<>(SingleFragmentActivity.class);

    @Before
    public void setUp(){
        activityTestRule.getActivity().setFragment(tvShowFragment);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void toDetailTvShowActivity(){
        onView(withId(R.id.tvShowList)).check(matches(isDisplayed()));
        onView(withId(R.id.tvShowList)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.ivPosterDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvVoteAverageDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvReleaseDateDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvOverviewDetail)).check(matches(isDisplayed()));
    }

}
