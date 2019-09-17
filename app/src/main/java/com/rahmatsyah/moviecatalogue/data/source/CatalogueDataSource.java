package com.rahmatsyah.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;

import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public interface CatalogueDataSource {

    LiveData<Resource<List<MovieEntity>>> getMovies();

    LiveData<Resource<List<TvShowEntity>>> getTvShows();

    LiveData<Resource<MovieEntity>> getMovie(long id);

    LiveData<Resource<TvShowEntity>> getTvShow(long id);

    LiveData<Resource<List<MovieEntity>>> getBookmarkedMovies();

    LiveData<Resource<List<TvShowEntity>>> getBookmarkedTvShows();

    void bookmark(MovieEntity movieEntity);

    void bookmark(TvShowEntity tvShowEntity);

}
