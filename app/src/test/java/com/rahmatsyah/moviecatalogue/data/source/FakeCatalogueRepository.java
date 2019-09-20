package com.rahmatsyah.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

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

public class FakeCatalogueRepository implements CatalogueDataSource{

    private volatile static FakeCatalogueRepository INSTANCE = null;

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;
    private final AppExecutor appExecutor;

    FakeCatalogueRepository(LocalRepository localRepository, RemoteRepository remoteRepository, AppExecutor appExecutor) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.appExecutor = appExecutor;
    }

    public static FakeCatalogueRepository getInstance(LocalRepository localRepository, RemoteRepository remoteRepository, AppExecutor appExecutor){
        if (INSTANCE == null){
            synchronized (CatalogueRepository.class){
                if (INSTANCE==null){
                    INSTANCE = new FakeCatalogueRepository(localRepository,remoteRepository,appExecutor);
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
    public LiveData<Resource<PagedList<MovieEntity>>> getBookmarkedMovies() {
        return new NetworkBoundResource<PagedList<MovieEntity>,List<MovieResponse>>(appExecutor){

            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getBookmarkedMovies(),10).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TvShowEntity>>> getBookmarkedTvShows() {
        return new NetworkBoundResource<PagedList<TvShowEntity>,List<TvShowResponse>>(appExecutor){

            @Override
            protected LiveData<PagedList<TvShowEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getBookmarkedTvShows(),10).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvShowEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TvShowResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TvShowResponse> data) {

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
