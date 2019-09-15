package com.rahmatsyah.moviecatalogue.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class TvShowResponse {
    @SerializedName("id")
    private long id;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("name")
    private String name;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    public TvShowResponse(long id, double voteAverage, String name, String firstAirDate, String overview, String posterPath) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.name = name;
        this.firstAirDate = firstAirDate;
        this.overview = overview;
        this.posterPath = posterPath;
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
