package com.rahmatsyah.moviecatalogue.ui.favorite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rahmatsyah.moviecatalogue.ui.favorite.movie.FavoriteMovieFragment;
import com.rahmatsyah.moviecatalogue.ui.favorite.tvshow.FavoriteTvShowFragment;

import java.util.ArrayList;

public class FavoritePagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public FavoritePagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FavoriteMovieFragment();
            case 1:
                return new FavoriteTvShowFragment();
                default:
                    return null;
        }
    }



    @Override
    public int getCount() {
        return tabCount;
    }
}
