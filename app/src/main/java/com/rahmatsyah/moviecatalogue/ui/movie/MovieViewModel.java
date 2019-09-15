package com.rahmatsyah.moviecatalogue.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;

import java.util.ArrayList;

public class MovieViewModel extends ViewModel {
    private CatalogueRepository catalogueRepository;

    public MovieViewModel(CatalogueRepository catalogueRepository){
        this.catalogueRepository = catalogueRepository;
    }

    LiveData<ArrayList<MovieEntity>> getMovies(){
        return catalogueRepository.getMovies();
    }
}
