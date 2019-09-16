package com.rahmatsyah.moviecatalogue.data.source.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.TvShowResponse;

@Entity(tableName = "TvShow")
public class TvShowEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "voteAverage")
    private double voteAverage;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "firstAirDate")
    private String firstAirDate;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "posterPath")
    private String posterPath;

    @ColumnInfo(name = "bookmark")
    private boolean isBookmarked = false;

    public TvShowEntity(long id, double voteAverage, String name, String firstAirDate, String overview, String posterPath, Boolean isBookmarked) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.name = name;
        this.firstAirDate = firstAirDate;
        this.overview = overview;
        this.posterPath = posterPath;
        if (isBookmarked!=null){
            this.isBookmarked = isBookmarked;
        }
    }

    public TvShowEntity(TvShowResponse tvShowResponse){
        id = tvShowResponse.getId();
        posterPath = tvShowResponse.getPosterPath();
        voteAverage = tvShowResponse.getVoteAverage();
        name = tvShowResponse.getName();
        firstAirDate = tvShowResponse.getFirstAirDate();
        overview = tvShowResponse.getOverview();
        isBookmarked = false;
    }

    public void bookmark(){
        isBookmarked = !isBookmarked;
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
