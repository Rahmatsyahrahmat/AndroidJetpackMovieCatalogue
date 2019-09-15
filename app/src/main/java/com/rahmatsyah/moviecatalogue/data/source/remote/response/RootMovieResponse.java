package com.rahmatsyah.moviecatalogue.data.source.remote.response;

import java.util.ArrayList;

public class RootMovieResponse {

    private ArrayList<MovieResponse> results;

    public RootMovieResponse(ArrayList<MovieResponse> results) {
        this.results = results;
    }

    public ArrayList<MovieResponse> getResults() {
        return results;
    }
}
