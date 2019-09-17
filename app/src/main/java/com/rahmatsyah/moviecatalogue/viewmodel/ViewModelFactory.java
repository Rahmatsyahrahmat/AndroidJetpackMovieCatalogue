package com.rahmatsyah.moviecatalogue.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.di.Injection;
import com.rahmatsyah.moviecatalogue.ui.detail.movie.DetailMovieViewModel;
import com.rahmatsyah.moviecatalogue.ui.detail.tvshow.DetailTvShowViewModel;
import com.rahmatsyah.moviecatalogue.ui.favorite.movie.FavoriteMovieViewModel;
import com.rahmatsyah.moviecatalogue.ui.favorite.tvshow.FavoriteTvShowViewModel;
import com.rahmatsyah.moviecatalogue.ui.movie.MovieViewModel;
import com.rahmatsyah.moviecatalogue.ui.tvshow.TvShowViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile  ViewModelFactory INSTANCE;

    private final CatalogueRepository catalogueRepository;

    private ViewModelFactory(CatalogueRepository catalogueRepository){
        this.catalogueRepository = catalogueRepository;
    }

    public static  ViewModelFactory getInstance(Application application){
        if (INSTANCE==null){
            synchronized (ViewModelFactory.class){
                if (INSTANCE==null){
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {


        if (modelClass.isAssignableFrom(MovieViewModel.class)){
            return (T) new MovieViewModel(catalogueRepository);
        }else if (modelClass.isAssignableFrom(TvShowViewModel.class)){
            return (T) new TvShowViewModel(catalogueRepository);
        }else if (modelClass.isAssignableFrom(DetailMovieViewModel.class)){
            return (T) new DetailMovieViewModel(catalogueRepository);
        }else if (modelClass.isAssignableFrom(DetailTvShowViewModel.class)){
            return (T) new DetailTvShowViewModel(catalogueRepository);
        }else if (modelClass.isAssignableFrom(FavoriteMovieViewModel.class)){
            return (T) new FavoriteMovieViewModel(catalogueRepository);
        }else if (modelClass.isAssignableFrom(FavoriteTvShowViewModel.class)){
            return (T) new FavoriteTvShowViewModel(catalogueRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: "+modelClass.getName());
    }
}
