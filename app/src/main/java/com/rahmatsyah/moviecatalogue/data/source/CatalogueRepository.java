package com.rahmatsyah.moviecatalogue.data.source;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rahmatsyah.moviecatalogue.data.source.local.LocalRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.data.source.remote.ApiResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.RemoteRepository;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.MovieResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.TvShowResponse;
import com.rahmatsyah.moviecatalogue.utils.AppExecutor;
import com.rahmatsyah.moviecatalogue.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class CatalogueRepository implements CatalogueDataSource{

    private volatile static CatalogueRepository INSTANCE = null;

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;
    private final AppExecutor appExecutor;

    private CatalogueRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository,@NonNull AppExecutor appExecutor){
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.appExecutor = appExecutor;
    }

    public static CatalogueRepository getInstance(LocalRepository localRepository, RemoteRepository remoteRepository, AppExecutor appExecutor){
        if (INSTANCE==null){
            synchronized (CatalogueRepository.class){
                if (INSTANCE==null){
                    INSTANCE = new CatalogueRepository(localRepository,remoteRepository,appExecutor);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getMovies() {
        return new NetworkBoundResource<List<MovieEntity>,List<MovieResponse>>(appExecutor){
            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.getMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return (data==null)||(data.size()==0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return remoteRepository.getMovies();
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {
                List<MovieEntity> movieEntities = new ArrayList<>();
                for (MovieResponse movieResponse:data){
                    movieEntities.add(new MovieEntity(movieResponse));
                }
                localRepository.insertMovies(movieEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<TvShowEntity>>> getTvShows() {
        return new NetworkBoundResource<List<TvShowEntity>,List<TvShowResponse>>(appExecutor){

            @Override
            protected LiveData<List<TvShowEntity>> loadFromDB() {
                return localRepository.getTvShows();
            }

            @Override
            protected Boolean shouldFetch(List<TvShowEntity> data) {
                return (data==null)||(data.size()==0);
            }

            @Override
            protected LiveData<ApiResponse<List<TvShowResponse>>> createCall() {
                return remoteRepository.getTvShows();
            }

            @Override
            protected void saveCallResult(List<TvShowResponse> data) {
                List<TvShowEntity> tvShowEntities = new ArrayList<>();

                for (TvShowResponse tvShowResponse:data){
                    tvShowEntities.add(new TvShowEntity(tvShowResponse));
                }

                localRepository.insertTvShows(tvShowEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> getMovie(long id) {
        return new NetworkBoundResource<MovieEntity,MovieResponse>(appExecutor){

            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localRepository.getMovieById(id);
            }

            @Override
            protected Boolean shouldFetch(MovieEntity data) {
                return data==null;
            }

            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return remoteRepository.getMovie(id);
            }

            @Override
            protected void saveCallResult(MovieResponse data) {
                MovieEntity movieEntity = new MovieEntity(data);
                List<MovieEntity> movieEntities = new ArrayList<>();
                movieEntities.add(movieEntity);
                localRepository.insertMovies(movieEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvShowEntity>> getTvShow(long id) {
        return new NetworkBoundResource<TvShowEntity,TvShowResponse>(appExecutor){

            @Override
            protected LiveData<TvShowEntity> loadFromDB() {
                return localRepository.getTvShowById(id);
            }

            @Override
            protected Boolean shouldFetch(TvShowEntity data) {
                return data==null;
            }

            @Override
            protected LiveData<ApiResponse<TvShowResponse>> createCall() {
                return remoteRepository.getTvShow(id);
            }

            @Override
            protected void saveCallResult(TvShowResponse data) {
                TvShowEntity tvShowEntity = new TvShowEntity(data);
                List<TvShowEntity> tvShowEntities = new ArrayList<>();
                tvShowEntities.add(tvShowEntity);
                localRepository.insertTvShows(tvShowEntities);
            }
        }.asLiveData();
    }

    @Override
    public void bookmark(MovieEntity movieEntity) {
        Runnable runnable = ()-> localRepository.bookmark(movieEntity);

        appExecutor.diskIO().execute(runnable);
    }

    @Override
    public void bookmark(TvShowEntity tvShowEntity) {
        Runnable runnable = ()-> localRepository.bookmark(tvShowEntity);

        appExecutor.diskIO().execute(runnable);
    }

}
