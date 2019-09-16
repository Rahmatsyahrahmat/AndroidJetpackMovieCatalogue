package com.rahmatsyah.moviecatalogue.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;

@Database(entities = {MovieEntity.class, TvShowEntity.class},version = 1, exportSchema = false)
public abstract class CatalogueDatabase extends RoomDatabase {

    private static CatalogueDatabase INSTANCE;

    public abstract CatalogueDao catalogueDao();

    private static final Object sLock = new Object();

    public static final CatalogueDatabase getInstance(Context context){
        synchronized (sLock){
            if (INSTANCE==null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),CatalogueDatabase.class,"Catalogue.db").build();
            }
        }
        return INSTANCE;
    }

}
