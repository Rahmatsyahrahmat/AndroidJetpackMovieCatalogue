package com.rahmatsyah.moviecatalogue.ui.favorite.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoriteMovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private FavoriteMovieViewModel favoriteMovieViewModel;

    @Before
    public void setUp(){
        favoriteMovieViewModel = new FavoriteMovieViewModel(catalogueRepository);
    }

    @Test
    public void getFavorite(){

        MutableLiveData<Resource<PagedList<MovieEntity>>> dummyMovie = new MutableLiveData<>();
        PagedList<MovieEntity> pagedList = mock(PagedList.class);

        dummyMovie.setValue(Resource.success(pagedList));

        when(catalogueRepository.getBookmarkedMovies()).thenReturn(dummyMovie);

        Observer<Resource<PagedList<MovieEntity>>> observer = mock(Observer.class);

        favoriteMovieViewModel.getBookmarkedMovies().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));

    }

}