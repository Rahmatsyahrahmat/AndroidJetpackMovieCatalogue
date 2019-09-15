package com.rahmatsyah.moviecatalogue.ui.detail.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;

public class DetailMovieViewModel extends ViewModel {
    private CatalogueRepository catalogueRepository;

    public DetailMovieViewModel(CatalogueRepository catalogueRepository){
        this.catalogueRepository = catalogueRepository;
    }

    LiveData<MovieEntity> getMovie(long id){
        return catalogueRepository.getMovie(id);
    }

}
