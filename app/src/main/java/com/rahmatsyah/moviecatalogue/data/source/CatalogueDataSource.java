package com.rahmatsyah.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;

import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;

import java.util.ArrayList;

public interface CatalogueDataSource {

    LiveData<ArrayList<MovieEntity>> getMovies();

    LiveData<ArrayList<TvShowEntity>> getTvShows();

    LiveData<MovieEntity> getMovie(long id);

    LiveData<TvShowEntity> getTvShow(long id);

}
