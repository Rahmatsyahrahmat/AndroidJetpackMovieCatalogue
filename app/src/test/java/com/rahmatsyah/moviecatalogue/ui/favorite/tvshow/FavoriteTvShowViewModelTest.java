package com.rahmatsyah.moviecatalogue.ui.favorite.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoriteTvShowViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private FavoriteTvShowViewModel viewModel;

    @Before
    public void setUp(){
        viewModel = new FavoriteTvShowViewModel(catalogueRepository);
    }

    @Test
    public void getBookmark(){
        MutableLiveData<Resource<PagedList<TvShowEntity>>> dummyTvShow = new MutableLiveData<>();
        PagedList<TvShowEntity> pagedList = mock(PagedList.class);

        dummyTvShow.setValue(Resource.success(pagedList));

        when(catalogueRepository.getBookmarkedTvShows()).thenReturn(dummyTvShow);

        Observer<Resource<PagedList<TvShowEntity>>> observer = mock(Observer.class);

        viewModel.getBookmarkedTvShows().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));
    }

}