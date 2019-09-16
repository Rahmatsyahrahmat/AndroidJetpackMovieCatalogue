package com.rahmatsyah.moviecatalogue.ui.detail.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.vo.Resource;


public class DetailTvShowViewModel extends ViewModel {
    private CatalogueRepository catalogueRepository;

    private MutableLiveData<Long> tvShowId = new MutableLiveData<>();

    private LiveData<Resource<TvShowEntity>> tvShow = Transformations.switchMap(tvShowId,mTvshowId->catalogueRepository.getTvShow(mTvshowId));

    public DetailTvShowViewModel(CatalogueRepository catalogueRepository){
        this.catalogueRepository = catalogueRepository;
    }

    public void setTvShowId(long tvShowId){
        this.tvShowId.setValue(tvShowId);
    }

    LiveData<Resource<TvShowEntity>> getTvShow(){
        return tvShow;
    }
    void bookmark(){
        catalogueRepository.bookmark(tvShow.getValue().data);
    }
}
