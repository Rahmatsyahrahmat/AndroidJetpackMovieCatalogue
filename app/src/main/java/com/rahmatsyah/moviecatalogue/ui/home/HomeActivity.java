package com.rahmatsyah.moviecatalogue.ui.home;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.ui.favorite.FavoriteFragment;
import com.rahmatsyah.moviecatalogue.ui.movie.MovieFragment;
import com.rahmatsyah.moviecatalogue.ui.tvshow.TvShowFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

    private final String SELECTED_MENU = "selected_menu";

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState!=null){
            savedInstanceState.getInt(SELECTED_MENU);
        }
        else {
            bottomNavigationView.setSelectedItemId(R.id.navigation_movies);
        }
        bottomNavigationView.setOnNavigationItemReselectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        switch (menuItem.getItemId()) {
            case R.id.navigation_movies:
                fragmentTransaction.replace(R.id.frameHome,new MovieFragment()).commit();
                return true;
            case R.id.navigation_tv_shows:
                fragmentTransaction.replace(R.id.frameHome,new TvShowFragment()).commit();
                return true;
            case R.id.navigation_favorite:
                fragmentTransaction.replace(R.id.frameHome,new FavoriteFragment()).commit();
                return true;
        }
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_MENU,bottomNavigationView.getSelectedItemId());
    }


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

    }
}
