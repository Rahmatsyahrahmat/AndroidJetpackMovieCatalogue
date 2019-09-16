package com.rahmatsyah.moviecatalogue.data.source.remote;

import com.rahmatsyah.moviecatalogue.data.source.remote.response.MovieResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.RootMovieResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.RootTvShowResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.TvShowResponse;
import com.rahmatsyah.moviecatalogue.utils.EspressoIdlingResource;
import com.rahmatsyah.moviecatalogue.utils.JsonHelper;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

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

    public LiveData<ApiResponse<List<MovieResponse>>> getMovies(){
        EspressoIdlingResource.increment();

        MutableLiveData<ApiResponse<List<MovieResponse>>> movieResult = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> jsonHelper.getMovies().enqueue(new Callback<RootMovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<RootMovieResponse> call, @NonNull Response<RootMovieResponse> response) {
                assert response.body() != null;
                movieResult.setValue(ApiResponse.success(response.body().getResults()));
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow())
                    EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<RootMovieResponse> call, @NonNull Throwable t) {
                EspressoIdlingResource.decrement();
            }
        }),SERVICE_LATENCY_IN_MILLIS);

        return movieResult;
    }

    public LiveData<ApiResponse<List<TvShowResponse>>> getTvShows(){
        EspressoIdlingResource.increment();

        MutableLiveData<ApiResponse<List<TvShowResponse>>> tvShowResult = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> jsonHelper.getTvShow().enqueue(new Callback<RootTvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<RootTvShowResponse> call, @NonNull Response<RootTvShowResponse> response) {
                assert response.body() != null;
                tvShowResult.setValue(ApiResponse.success(response.body().getResults()));
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow())
                    EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<RootTvShowResponse> call, @NonNull Throwable t) {
                EspressoIdlingResource.decrement();
            }
        }),SERVICE_LATENCY_IN_MILLIS);

        return tvShowResult;
    }

    public LiveData<ApiResponse<MovieResponse>> getMovie(long id){
        EspressoIdlingResource.increment();

        MutableLiveData<ApiResponse<MovieResponse>> movieResult = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(()-> jsonHelper.getMovieById(id).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                movieResult.setValue(ApiResponse.success(response.body()));
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow())
                    EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                EspressoIdlingResource.decrement();
            }
        }),SERVICE_LATENCY_IN_MILLIS);
        return movieResult;
    }

    public LiveData<ApiResponse<TvShowResponse>> getTvShow(long id){
        EspressoIdlingResource.increment();

        MutableLiveData<ApiResponse<TvShowResponse>> tvShowResult = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(()-> jsonHelper.getTvShowById(id).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                tvShowResult.setValue(ApiResponse.success(response.body()));
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow())
                    EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                EspressoIdlingResource.decrement();
            }
        }),SERVICE_LATENCY_IN_MILLIS);

        return tvShowResult;
    }


}
