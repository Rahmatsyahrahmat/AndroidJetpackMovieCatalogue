package com.rahmatsyah.moviecatalogue.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rahmatsyah.moviecatalogue.data.source.local.LocalRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.data.source.remote.RemoteRepository;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.MovieResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.TvShowResponse;

import java.util.ArrayList;

public class CatalogueRepository implements CatalogueDataSource{

    private volatile static CatalogueRepository INSTANCE = null;

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;

    private CatalogueRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository){
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public static CatalogueRepository getInstance(LocalRepository localRepository, RemoteRepository remoteRepository){
        if (INSTANCE==null){
            synchronized (CatalogueRepository.class){
                if (INSTANCE==null){
                    INSTANCE = new CatalogueRepository(localRepository,remoteRepository);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<ArrayList<MovieEntity>> getMovies() {
        MutableLiveData<ArrayList<MovieEntity>> movieResult = new MutableLiveData<>();

        remoteRepository.getMovies(new RemoteRepository.LoadMoviesCallback() {
            @Override
            public void onAllMovieReceived(ArrayList<MovieResponse> movieResponses) {
                ArrayList<MovieEntity> movieEntities = new ArrayList<>();
                for (MovieResponse movieResponse:movieResponses){
                    movieEntities.add(new MovieEntity(movieResponse));
                }
                movieResult.postValue(movieEntities);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return movieResult;
    }

    @Override
    public LiveData<ArrayList<TvShowEntity>> getTvShows() {
        MutableLiveData<ArrayList<TvShowEntity>> tvShowResult = new MutableLiveData<>();

        remoteRepository.getTvShows(new RemoteRepository.LoadTvShowsCallback() {
            @Override
            public void onAllTvShowReceived(ArrayList<TvShowResponse> tvShowResponses) {
                ArrayList<TvShowEntity> tvShowEntities = new ArrayList<>();
                for (TvShowResponse tvShowResponse:tvShowResponses){
                    tvShowEntities.add(new TvShowEntity(tvShowResponse));
                }
                tvShowResult.postValue(tvShowEntities);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return tvShowResult;
    }

    @Override
    public LiveData<MovieEntity> getMovie(long id) {
        MutableLiveData<MovieEntity> movieResult = new MutableLiveData<>();

        remoteRepository.getMovie(id, new RemoteRepository.LoadMovieCallback() {
            @Override
            public void onMovieReceived(MovieResponse movieResponse) {
                MovieEntity movieEntity = new MovieEntity(movieResponse);
                movieResult.postValue(movieEntity);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return movieResult;
    }

    @Override
    public LiveData<TvShowEntity> getTvShow(long id) {
        MutableLiveData<TvShowEntity> tvShowResult = new MutableLiveData<>();

        remoteRepository.getTvShow(id, new RemoteRepository.LoadTvShowCallback() {
            @Override
            public void onTvShowReceived(TvShowResponse tvShowResponse) {
                TvShowEntity tvShowEntity = new TvShowEntity(tvShowResponse);
                tvShowResult.postValue(tvShowEntity);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return tvShowResult;
    }

}
