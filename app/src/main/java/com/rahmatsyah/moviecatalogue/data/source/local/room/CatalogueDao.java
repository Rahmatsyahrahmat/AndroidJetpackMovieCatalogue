package com.rahmatsyah.moviecatalogue.data.source.local.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;

import java.util.List;

@Dao
public interface CatalogueDao {

    @Query("SELECT * FROM movie")
    LiveData<List<MovieEntity>> getMovies();

    @Query("SELECT * FROM tvshow")
    LiveData<List<TvShowEntity>> getTvShows();

    @Query("SELECT * FROM movie WHERE id=:id")
    LiveData<MovieEntity> getMovieById(long id);

    @Query("SELECT * FROM tvshow WHERE id=:id")
    LiveData<TvShowEntity> getTvShowById(long id);

    @Query("SELECT * FROM movie WHERE bookmark=1")
    DataSource.Factory<Integer, MovieEntity> getBookmarkedMovies();

    @Query("SELECT * FROM tvshow WHERE bookmark=1")
    DataSource.Factory<Integer, TvShowEntity> getBookmarkedTvShows();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<MovieEntity> movieEntity);

    @Delete
    void delete(MovieEntity movieEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTvShows(List<TvShowEntity> tvShowEntity);

    @Delete
    void delete(TvShowEntity tvShowEntity);

    @Update(onConflict = OnConflictStrategy.FAIL)
    void update(MovieEntity movieEntity);

    @Update(onConflict = OnConflictStrategy.FAIL)
    void update(TvShowEntity tvShowEntity);
}
