package com.rahmatsyah.moviecatalogue.data.source.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.MovieResponse;

@Entity(tableName = "Movie")
public class MovieEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "voteAverage")
    private double voteAverage;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "releaseDate")
    private String releaseDate;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "posterPath")
    private String posterPath;

    @ColumnInfo(name = "bookmark")
    private boolean isBookmarked = false;

    public MovieEntity(long id, double voteAverage, String title, String releaseDate, String overview, String posterPath, Boolean isBookmarked) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.title = title;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.posterPath = posterPath;
        if (isBookmarked!=null){
            this.isBookmarked = isBookmarked;
        }
    }

    public MovieEntity(MovieResponse movieResponse){
        id = movieResponse.getId();
        posterPath = movieResponse.getPosterPath();
        voteAverage = movieResponse.getVoteAverage();
        title = movieResponse.getTitle();
        releaseDate = movieResponse.getReleaseDate();
        overview = movieResponse.getOverview();
        isBookmarked = false;
    }

    public void bookmark(){
        isBookmarked = !isBookmarked;
    }

    public void setBookmarked(boolean isBookmarked) {
        this.isBookmarked = isBookmarked;
    }

    public boolean isBookmarked() {
        return isBookmarked;
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
