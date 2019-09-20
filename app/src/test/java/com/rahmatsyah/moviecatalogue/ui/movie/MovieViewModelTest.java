package com.rahmatsyah.moviecatalogue.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.utils.FakeDataDummy;
import com.rahmatsyah.moviecatalogue.vo.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieViewModel movieViewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);

    @Before
    public void setUp(){
        movieViewModel = new MovieViewModel(catalogueRepository);
    }

    @After
    public void terDown(){

    }

    @Test
    public void getMovies(){
        Resource<List<MovieEntity>> resource = Resource.success(FakeDataDummy.generateDummyMovie());
        MutableLiveData<Resource<List<MovieEntity>>> dummyMovie = new MutableLiveData<>();
        dummyMovie.setValue(resource);

        when(catalogueRepository.getMovies()).thenReturn(dummyMovie);

        Observer<Resource<List<MovieEntity>>> observer = mock(Observer.class);

        movieViewModel.getMovies().observeForever(observer);

        verify(observer).onChanged(resource);
    }

}