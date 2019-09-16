package com.rahmatsyah.moviecatalogue.data.source.local;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.data.source.local.room.CatalogueDao;

import java.util.List;

public class LocalRepository {

   private final CatalogueDao catalogueDao;

   private LocalRepository(CatalogueDao catalogueDao){
       this.catalogueDao = catalogueDao;
   }

   private static LocalRepository INSTANCE;

   public static LocalRepository getInstance(CatalogueDao catalogueDao){
       if (INSTANCE==null){
           INSTANCE = new LocalRepository(catalogueDao);
       }
       return INSTANCE;
   }

   public LiveData<List<MovieEntity>> getMovies(){
       return catalogueDao.getMovies();
   }

   public LiveData<List<TvShowEntity>> getTvShows(){
       return catalogueDao.getTvShows();
   }

   public LiveData<MovieEntity> getMovieById(long id){
       return catalogueDao.getMovieById(id);
   }

   public LiveData<TvShowEntity> getTvShowById(long id){
       return catalogueDao.getTvShowById(id);
   }

   public LiveData<MovieEntity> getBookmarkedMovies(){
       return catalogueDao.getBookmarkedMovies();
   }

   public LiveData<TvShowEntity> getBookmarkedTvShows(){
       return catalogueDao.getBookmarkedTvShows();
   }

   public void insertMovies(List<MovieEntity> movieEntity) {
       catalogueDao.insertMovies(movieEntity);
   }

   public void insertTvShows(List<TvShowEntity> tvShowEntity){
       catalogueDao.insertTvShows(tvShowEntity);
   }

   public void delete(MovieEntity movieEntity){
       catalogueDao.delete(movieEntity);
   }

   public void delete(TvShowEntity tvShowEntity){
       catalogueDao.delete(tvShowEntity);
   }

   public void update(MovieEntity movieEntity){
       catalogueDao.update(movieEntity);
   }

   public void update(TvShowEntity tvShowEntity){
       catalogueDao.update(tvShowEntity);
   }

   public void bookmark(MovieEntity movieEntity){
       movieEntity.bookmark();
       update(movieEntity);
   }

   public void bookmark(TvShowEntity tvShowEntity){
       tvShowEntity.bookmark();
       update(tvShowEntity);
   }

}
