package com.rahmatsyah.moviecatalogue.data.source.remote;

import com.rahmatsyah.moviecatalogue.data.source.remote.response.MovieResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.RootMovieResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.RootTvShowResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.TvShowResponse;
import com.rahmatsyah.moviecatalogue.utils.EspressoIdlingResource;
import com.rahmatsyah.moviecatalogue.utils.JsonHelper;

import android.os.Handler;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {

    private static RemoteRepository INSTANCE;
    private JsonHelper jsonHelper;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteRepository(JsonHelper jsonHelper){
        this.jsonHelper = jsonHelper;
    }
    public static RemoteRepository getInstance(JsonHelper jsonHelper){
        if (INSTANCE == null){
            INSTANCE = new RemoteRepository(jsonHelper);
        }
        return INSTANCE;
    }

    public void getMovies(LoadMoviesCallback loadMoviesCallback){
        EspressoIdlingResource.increment();
        Handler handler = new Handler();
        handler.postDelayed(() -> jsonHelper.getMovies().enqueue(new Callback<RootMovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<RootMovieResponse> call, @NonNull Response<RootMovieResponse> response) {
                assert response.body() != null;
                loadMoviesCallback.onAllMovieReceived(response.body().getResults());
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<RootMovieResponse> call, @NonNull Throwable t) {
                loadMoviesCallback.onDataNotAvailable();
                EspressoIdlingResource.decrement();
            }
        }),SERVICE_LATENCY_IN_MILLIS);
    }

    public void getTvShows(LoadTvShowsCallback loadTvShowsCallback){
        EspressoIdlingResource.increment();
        Handler handler = new Handler();
        handler.postDelayed(() -> jsonHelper.getTvShow().enqueue(new Callback<RootTvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<RootTvShowResponse> call, @NonNull Response<RootTvShowResponse> response) {
                assert response.body() != null;
                loadTvShowsCallback.onAllTvShowReceived(response.body().getResults());
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<RootTvShowResponse> call, @NonNull Throwable t) {
                loadTvShowsCallback.onDataNotAvailable();
                EspressoIdlingResource.decrement();
            }
        }),SERVICE_LATENCY_IN_MILLIS);
    }

    public void getMovie(long id, LoadMovieCallback loadMovieCallback){
        EspressoIdlingResource.increment();
        Handler handler = new Handler();
        handler.postDelayed(()-> jsonHelper.getMovieById(id).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                loadMovieCallback.onMovieReceived(response.body());
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                loadMovieCallback.onDataNotAvailable();
                EspressoIdlingResource.decrement();
            }
        }),SERVICE_LATENCY_IN_MILLIS);
    }

    public void getTvShow(long id, LoadTvShowCallback loadTvShowCallback){
        EspressoIdlingResource.increment();
        Handler handler = new Handler();
        handler.postDelayed(()-> jsonHelper.getTvShowById(id).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                loadTvShowCallback.onTvShowReceived(response.body());
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                loadTvShowCallback.onDataNotAvailable();
                EspressoIdlingResource.decrement();
            }
        }),SERVICE_LATENCY_IN_MILLIS);
    }


    public interface LoadMoviesCallback {
        void onAllMovieReceived(ArrayList<MovieResponse> movieResponses);

        void onDataNotAvailable();
    }

    public interface LoadTvShowsCallback {
        void onAllTvShowReceived(ArrayList<TvShowResponse> tvShowResponses);

        void onDataNotAvailable();
    }

    public interface LoadMovieCallback{
        void onMovieReceived(MovieResponse movieResponse);

        void onDataNotAvailable();
    }

    public interface LoadTvShowCallback{
        void onTvShowReceived(TvShowResponse tvShowResponse);

        void onDataNotAvailable();
    }

}
