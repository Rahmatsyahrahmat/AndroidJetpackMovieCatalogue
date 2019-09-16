package com.rahmatsyah.moviecatalogue.di;

import android.app.Application;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.LocalRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.room.CatalogueDatabase;
import com.rahmatsyah.moviecatalogue.utils.AppExecutor;
import com.rahmatsyah.moviecatalogue.utils.JsonHelper;
import com.rahmatsyah.moviecatalogue.data.source.remote.RemoteRepository;

public class Injection {

    public static CatalogueRepository provideRepository(Application application){
        CatalogueDatabase catalogueDatabase = CatalogueDatabase.getInstance(application);

        LocalRepository localRepository = LocalRepository.getInstance(catalogueDatabase.catalogueDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(JsonHelper.getInstance());
        AppExecutor appExecutor = new AppExecutor();
        return CatalogueRepository.getInstance(localRepository,remoteRepository,appExecutor);
    }

}
