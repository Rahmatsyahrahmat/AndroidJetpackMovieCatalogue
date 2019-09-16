package com.rahmatsyah.moviecatalogue.data.source.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.rahmatsyah.moviecatalogue.data.source.remote.StatusResponse.EMPTY;
import static com.rahmatsyah.moviecatalogue.data.source.remote.StatusResponse.ERROR;
import static com.rahmatsyah.moviecatalogue.data.source.remote.StatusResponse.SUCCESS;

public class ApiResponse<T> {

    @NonNull
    public final StatusResponse statusResponse;

    @Nullable
    public final String message;

    @Nullable
    public final T body;

    public ApiResponse(@NonNull StatusResponse statusResponse, @Nullable String message, @Nullable T body) {
        this.statusResponse = statusResponse;
        this.message = message;
        this.body = body;
    }

    public static <T> ApiResponse<T> success(@Nullable T body){
        return new ApiResponse<>(SUCCESS,null,body);
    }

    public static <T> ApiResponse<T> error(String message, @Nullable T body){
        return new ApiResponse<>(ERROR,message,body);
    }

    public static <T> ApiResponse<T> empty(String message, @Nullable T body){
        return new ApiResponse<>(EMPTY,message,body);
    }
}
