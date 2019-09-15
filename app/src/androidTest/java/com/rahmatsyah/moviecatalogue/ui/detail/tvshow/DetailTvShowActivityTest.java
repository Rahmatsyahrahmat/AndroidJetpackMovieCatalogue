package com.rahmatsyah.moviecatalogue.ui.detail.tvshow;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.utils.EspressoIdlingResource;
import com.rahmatsyah.moviecatalogue.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DetailTvShowActivityTest {

    private TvShowEntity dummyTvShow = FakeDataDummy.getTvShowEntity();

    @Rule
    public ActivityTestRule<DetailTvShowActivity> activityTestRule = new ActivityTestRule<DetailTvShowActivity>(DetailTvShowActivity.class){
        @Override
        protected Intent getActivityIntent() {
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(context,DetailTvShowActivity.class);
            result.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW_ID,dummyTvShow.getId());
            return result;
        }
    };

    @Before
    public void setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovie(){
        onView(withId(R.id.ivPosterDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvVoteAverageDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvReleaseDateDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvOverviewDetail)).check(matches(isDisplayed()));
    }

}