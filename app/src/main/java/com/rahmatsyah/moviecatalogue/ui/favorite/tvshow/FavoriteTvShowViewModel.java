package com.rahmatsyah.moviecatalogue.ui.favorite.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.ui.tvshow.TvShowAdapter;
import com.rahmatsyah.moviecatalogue.vo.Resource;

import java.util.List;

public class FavoriteTvShowViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;

    public FavoriteTvShowViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    LiveData<Resource<PagedList<TvShowEntity>>> getBookmarkedTvShows(){
        return catalogueRepository.getBookmarkedTvShows();
    }
}
