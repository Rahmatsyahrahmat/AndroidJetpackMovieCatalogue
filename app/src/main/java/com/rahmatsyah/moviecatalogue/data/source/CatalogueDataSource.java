package com.rahmatsyah.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

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

    LiveData<Resource<PagedList<MovieEntity>>> getBookmarkedMovies();

    LiveData<Resource<PagedList<TvShowEntity>>> getBookmarkedTvShows();

    void bookmark(MovieEntity movieEntity);

    void bookmark(TvShowEntity tvShowEntity);

}
