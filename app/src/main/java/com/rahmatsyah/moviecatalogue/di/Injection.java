package com.rahmatsyah.moviecatalogue.di;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.LocalRepository;
import com.rahmatsyah.moviecatalogue.utils.JsonHelper;
import com.rahmatsyah.moviecatalogue.data.source.remote.RemoteRepository;

public class Injection {

    public static CatalogueRepository provideRepository(){
        LocalRepository localRepository = new LocalRepository();
        RemoteRepository remoteRepository = RemoteRepository.getInstance(JsonHelper.getInstance());
        return CatalogueRepository.getInstance(localRepository,remoteRepository);
    }

}
