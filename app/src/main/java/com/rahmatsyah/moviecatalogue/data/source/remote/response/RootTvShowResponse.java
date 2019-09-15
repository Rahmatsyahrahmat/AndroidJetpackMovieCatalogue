package com.rahmatsyah.moviecatalogue.data.source.remote.response;

import java.util.ArrayList;

public class RootTvShowResponse {

    private ArrayList<TvShowResponse> results;

    public RootTvShowResponse(ArrayList<TvShowResponse> results) {
        this.results = results;
    }

    public ArrayList<TvShowResponse> getResults() {
        return results;
    }
}
