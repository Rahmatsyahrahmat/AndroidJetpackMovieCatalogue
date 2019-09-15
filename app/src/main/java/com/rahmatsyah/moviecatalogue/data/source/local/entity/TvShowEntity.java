package com.rahmatsyah.moviecatalogue.data.source.local.entity;

import com.rahmatsyah.moviecatalogue.data.source.remote.response.TvShowResponse;

public class TvShowEntity {
    private long id;

    private double voteAverage;

    private String name;

    private String firstAirDate;

    private String overview;

    private String posterPath;

    public TvShowEntity(long id, double voteAverage, String name, String firstAirDate, String overview, String posterPath) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.name = name;
        this.firstAirDate = firstAirDate;
        this.overview = overview;
        this.posterPath = posterPath;
    }

    public TvShowEntity(TvShowResponse tvShowResponse){
        id = tvShowResponse.getId();
        posterPath = tvShowResponse.getPosterPath();
        voteAverage = tvShowResponse.getVoteAverage();
        name = tvShowResponse.getName();
        firstAirDate = tvShowResponse.getFirstAirDate();
        overview = tvShowResponse.getOverview();
    }

    public long getId() {
        return id;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getName() {
        return name;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
