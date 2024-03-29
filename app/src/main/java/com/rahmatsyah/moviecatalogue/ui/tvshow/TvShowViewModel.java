package com.rahmatsyah.moviecatalogue.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class TvShowViewModel extends ViewModel {
    private CatalogueRepository catalogueRepository;

    public TvShowViewModel(CatalogueRepository catalogueRepository){
        this.catalogueRepository = catalogueRepository;
    }

    LiveData<Resource<List<TvShowEntity>>> getTvShows(){
        return catalogueRepository.getTvShows();
    }
}
