package com.rahmatsyah.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.rahmatsyah.moviecatalogue.data.source.remote.ApiResponse;
import com.rahmatsyah.moviecatalogue.utils.AppExecutor;
import com.rahmatsyah.moviecatalogue.vo.Resource;

public abstract class NetworkBoundResource<ResultType,RequestType> {

    private MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    private AppExecutor executor;

    protected void onFetchFailed(){

    }

    protected abstract LiveData<ResultType> loadFromDB();

    protected abstract Boolean shouldFetch(ResultType data);

    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    protected abstract void saveCallResult(RequestType data);

    public NetworkBoundResource(AppExecutor appExecutor){
        executor = appExecutor;

        result.setValue(Resource.loading(null));

        LiveData<ResultType> dbSource= loadFromDB();

        result.addSource(dbSource, data->{
            result.removeSource(dbSource);
            if (shouldFetch(data)){
                fetchFromNetwork(dbSource);
            }else {
                result.addSource(dbSource,newData->result.setValue(Resource.success(newData)));
            }
        });
    }

    private void fetchFromNetwork(LiveData<ResultType> dbSource){
        LiveData<ApiResponse<RequestType>> apiResponse = createCall();

        result.addSource(dbSource,newData->result.setValue(Resource.loading(newData)));

        result.addSource(apiResponse,response->{
            result.removeSource(apiResponse);
            result.removeSource(dbSource);

            switch (response.statusResponse){
                case SUCCESS:
                    executor.diskIO().execute(()->{
                        saveCallResult(response.body);

                        executor.mainThread().execute(()->result.addSource(loadFromDB(),newData->result.setValue(Resource.success(newData))));
                    });
                    break;

                case EMPTY:
                    executor.mainThread().execute(()->result.addSource(loadFromDB(),newData->result.setValue(Resource.success(newData))));
                    break;

                case ERROR:
                    onFetchFailed();
                    result.addSource(dbSource,newData->result.setValue(Resource.error(response.message,newData)));
                    break;
            }
        });
    }

    public LiveData<Resource<ResultType>> asLiveData(){
        return result;
    }
}
