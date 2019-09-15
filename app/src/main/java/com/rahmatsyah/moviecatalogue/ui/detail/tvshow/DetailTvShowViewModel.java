package com.rahmatsyah.moviecatalogue.ui.detail.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;


public class DetailTvShowViewModel extends ViewModel {
    private CatalogueRepository catalogueRepository;

    public DetailTvShowViewModel(CatalogueRepository catalogueRepository){
        this.catalogueRepository = catalogueRepository;
    }

    LiveData<TvShowEntity> getTvShow(long id){
        return catalogueRepository.getTvShow(id);
    }
}
