package com.rahmatsyah.moviecatalogue.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.rahmatsyah.moviecatalogue.data.source.local.LocalRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.data.source.remote.RemoteRepository;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.MovieResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.TvShowResponse;
import com.rahmatsyah.moviecatalogue.utils.FakeDataDummy;
import com.rahmatsyah.moviecatalogue.utils.InstantAppExecutor;
import com.rahmatsyah.moviecatalogue.utils.LiveDataTestUtil;
import com.rahmatsyah.moviecatalogue.utils.PagedListUtil;
import com.rahmatsyah.moviecatalogue.vo.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CatalogueRepositoryTest  {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private LocalRepository localRepository = mock(LocalRepository.class);
    private RemoteRepository remoteRepository = mock(RemoteRepository.class);
    private InstantAppExecutor instantAppExecutor = mock(InstantAppExecutor.class);
    private FakeCatalogueRepository catalogueRepository = new FakeCatalogueRepository(localRepository,remoteRepository,instantAppExecutor);

    private ArrayList<MovieResponse> movieResponses = FakeDataDummy.generateRemoteDummyMovie();
    private MovieResponse movieResponse = movieResponses.get(0);
    private ArrayList<TvShowResponse> tvShowResponses = FakeDataDummy.generateRemoteDummyTvShow();
    private TvShowResponse tvShowResponse = tvShowResponses.get(0);

    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){

    }

    @Test
    public void getMovies() {
        MutableLiveData<List<MovieEntity>> dummyMovie = new MutableLiveData<>();
        dummyMovie.setValue(FakeDataDummy.generateDummyMovie());

        when(localRepository.getMovies()).thenReturn(dummyMovie);

        Resource<List<MovieEntity>> result = LiveDataTestUtil.getValue(catalogueRepository.getMovies());

        verify(localRepository).getMovies();
        assertNotNull(result.data);
        assertEquals(movieResponses.size(),result.data.size());
    }

    @Test
    public void getTvShows() {
        MutableLiveData<List<TvShowEntity>> dummyTvShow = new MutableLiveData<>();
        dummyTvShow.setValue(FakeDataDummy.generateDummyTvShow());

        when(localRepository.getTvShows()).thenReturn(dummyTvShow);

        Resource<List<TvShowEntity>> result = LiveDataTestUtil.getValue(catalogueRepository.getTvShows());

        verify(localRepository).getTvShows();
        assertNotNull(result.data);
        assertEquals(tvShowResponses.size(),result.data.size());
    }

    @Test
    public void getMovie() {
        MutableLiveData<MovieEntity> dummyMovie = new MutableLiveData<>();
        dummyMovie.setValue(FakeDataDummy.generateDummyMovie().get(0));

        when(localRepository.getMovieById(movieResponse.getId())).thenReturn(dummyMovie);

        Resource<MovieEntity> result = LiveDataTestUtil.getValue(catalogueRepository.getMovie(movieResponse.getId()));

        verify(localRepository).getMovieById(movieResponse.getId());

        assertNotNull(result);

        assertNotNull(result.data);
        assertEquals(result.data.getId(),movieResponse.getId());
    }

    @Test
    public void getTvShow() {
        MutableLiveData<TvShowEntity> dummyTvShow = new MutableLiveData<>();
        dummyTvShow.setValue(FakeDataDummy.generateDummyTvShow().get(0));

        when(localRepository.getTvShowById(tvShowResponse.getId())).thenReturn(dummyTvShow);

        Resource<TvShowEntity> result = LiveDataTestUtil.getValue(catalogueRepository.getTvShow(tvShowResponse.getId()));

        verify(localRepository).getTvShowById(tvShowResponse.getId());

        assertNotNull(result);
        assertNotNull(result.data);
        assertEquals(result.data.getId(),tvShowResponse.getId());
    }

    @Test
    public void getBookmarkedMovies() {
        DataSource.Factory<Integer,MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(localRepository.getBookmarkedMovies()).thenReturn(dataSourceFactory);
        catalogueRepository.getBookmarkedMovies();
        Resource<PagedList<MovieEntity>> result = Resource.success(PagedListUtil.mockPagedList(FakeDataDummy.generateDummyMovie()));

        verify(localRepository).getBookmarkedMovies();
        assertNotNull(result.data);
        assertEquals(FakeDataDummy.generateDummyMovie().size(),result.data.size());
    }

    @Test
    public void getBookmarkedTvShows() {
        DataSource.Factory<Integer, TvShowEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(localRepository.getBookmarkedTvShows()).thenReturn(dataSourceFactory);
        catalogueRepository.getBookmarkedTvShows();
        Resource<PagedList<TvShowEntity>> result = Resource.success(PagedListUtil.mockPagedList(FakeDataDummy.generateDummyTvShow()));

        verify(localRepository).getBookmarkedTvShows();
        assertNotNull(result.data);
        assertEquals(FakeDataDummy.generateDummyTvShow().size(),result.data.size());
    }


}