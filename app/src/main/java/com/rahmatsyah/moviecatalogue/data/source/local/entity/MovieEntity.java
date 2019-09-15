package com.rahmatsyah.moviecatalogue.data.source.local.entity;

import com.rahmatsyah.moviecatalogue.data.source.remote.response.MovieResponse;

public class MovieEntity {

    private long id;

    private double voteAverage;

    private String title;

    private String releaseDate;

    private String overview;

    private String posterPath;

    public MovieEntity(long id, double voteAverage, String title, String releaseDate, String overview, String posterPath) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.title = title;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.posterPath = posterPath;
    }

    public MovieEntity(MovieResponse movieResponse){
        id = movieResponse.getId();
        posterPath = movieResponse.getPosterPath();
        voteAverage = movieResponse.getVoteAverage();
        title = movieResponse.getTitle();
        releaseDate = movieResponse.getReleaseDate();
        overview = movieResponse.getOverview();
    }

    public long getId() {
        return id;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
