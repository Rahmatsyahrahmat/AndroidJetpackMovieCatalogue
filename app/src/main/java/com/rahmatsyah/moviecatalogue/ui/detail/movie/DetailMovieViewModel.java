package com.rahmatsyah.moviecatalogue.ui.detail.movie;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.vo.Resource;

import java.util.Objects;

public class DetailMovieViewModel extends ViewModel {
    private CatalogueRepository catalogueRepository;

    private MutableLiveData<Long> movieId = new MutableLiveData<>();

    private LiveData<Resource<MovieEntity>> movie = Transformations.switchMap(movieId, mMovieId -> catalogueRepository.getMovie(mMovieId));

    public DetailMovieViewModel(CatalogueRepository catalogueRepository){
        this.catalogueRepository = catalogueRepository;
    }

    public void setMovieId(long movieId){
        this.movieId.setValue(movieId);
    }

    LiveData<Resource<MovieEntity>> getMovie(){
        return movie;
    }

    void bookmark(){
        catalogueRepository.bookmark(movie.getValue().data);
    }

}
