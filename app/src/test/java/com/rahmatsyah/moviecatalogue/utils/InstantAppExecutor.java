package com.rahmatsyah.moviecatalogue.utils;

import java.util.concurrent.Executor;

public class InstantAppExecutor extends AppExecutor {
    private static Executor instant = Runnable::run;

    public InstantAppExecutor(){
        super(instant,instant,instant);
    }

}
