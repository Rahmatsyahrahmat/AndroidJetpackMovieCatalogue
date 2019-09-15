package com.rahmatsyah.moviecatalogue.ui.detail.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailMovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailMovieViewModel detailMovieViewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private MovieEntity dummyMovie = FakeDataDummy.generateDummyMovie().get(0);

    @Before
    public void setUp(){
        detailMovieViewModel = new DetailMovieViewModel(catalogueRepository);
    }

    @After
    public void tearDown(){}

    @Test
    public void getMovie(){
        MutableLiveData<MovieEntity> movieEntityMutableLiveData = new MutableLiveData<>();
        movieEntityMutableLiveData.setValue(dummyMovie);

        when(catalogueRepository.getMovie(dummyMovie.getId())).thenReturn(movieEntityMutableLiveData);

        Observer<MovieEntity> observer = mock(Observer.class);

        detailMovieViewModel.getMovie(dummyMovie.getId()).observeForever(observer);

        verify(observer).onChanged(dummyMovie);
    }
}