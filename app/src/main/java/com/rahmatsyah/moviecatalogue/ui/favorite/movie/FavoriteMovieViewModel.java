package com.rahmatsyah.moviecatalogue.ui.favorite.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.vo.Resource;

import java.util.List;

public class FavoriteMovieViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;

    public FavoriteMovieViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    LiveData<Resource<List<MovieEntity>>> getBookmarkedMovies(){
        return catalogueRepository.getBookmarkedMovies();
    }
}
