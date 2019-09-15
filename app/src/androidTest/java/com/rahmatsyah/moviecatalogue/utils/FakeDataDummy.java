package com.rahmatsyah.moviecatalogue.utils;


import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;

public class FakeDataDummy {
    private static MovieEntity movieEntity = new MovieEntity(
            429203,
            6.4,
            "The Old Man & the Gun",
            "2018-09-27",
            "The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public. Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, who loves him in spite of his chosen profession.",
            "/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg"
    );

    public static MovieEntity getMovieEntity(){
        return movieEntity;
    }

    private static TvShowEntity tvShowEntity = new TvShowEntity(
            60735,
            6.6,
            "The Flash",
            "2014-10-07",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "/fki3kBlwJzFp8QohL43g9ReV455.jpg"
    );

    public static TvShowEntity getTvShowEntity() {
        return tvShowEntity;
    }
}
