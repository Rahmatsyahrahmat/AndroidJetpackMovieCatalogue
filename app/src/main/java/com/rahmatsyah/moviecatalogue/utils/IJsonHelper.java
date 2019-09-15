package com.rahmatsyah.moviecatalogue.utils;

import com.rahmatsyah.moviecatalogue.BuildConfig;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.MovieResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.RootMovieResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.RootTvShowResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IJsonHelper {

    @GET("discover/movie?api_key="+ BuildConfig.ApiKey)
    Call<RootMovieResponse> getAllMovie();
    @GET("discover/tv?api_key="+BuildConfig.ApiKey)
    Call<RootTvShowResponse> getAllTvShow();
    @GET("movie/{id}?api_key="+BuildConfig.ApiKey)
    Call<MovieResponse> getMovieById(@Path("id") long id);
    @GET("tv/{id}?api_key="+BuildConfig.ApiKey)
    Call<TvShowResponse> getTvShowById(@Path("id") long id);

}
