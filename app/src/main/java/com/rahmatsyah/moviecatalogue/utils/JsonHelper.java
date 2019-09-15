package com.rahmatsyah.moviecatalogue.utils;

import com.rahmatsyah.moviecatalogue.data.source.remote.response.MovieResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.RootMovieResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.RootTvShowResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.TvShowResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonHelper {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static JsonHelper jsonHelper = new JsonHelper();
    private static IJsonHelper service;

    private JsonHelper(){
        Retrofit retrofit = createAdapter();
        service = retrofit.create(IJsonHelper.class);
    }

    private Retrofit createAdapter(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static JsonHelper getInstance(){
        return jsonHelper;
    }

    public Call<RootMovieResponse> getMovies(){
        return service.getAllMovie();
    }

    public Call<RootTvShowResponse> getTvShow(){
        return service.getAllTvShow();
    }

    public Call<MovieResponse> getMovieById(long id){
        return service.getMovieById(id);
    }

    public Call<TvShowResponse> getTvShowById(long id){
        return service.getTvShowById(id);
    }

}
