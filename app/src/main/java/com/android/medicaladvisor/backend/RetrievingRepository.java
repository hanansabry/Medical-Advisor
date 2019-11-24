package com.android.medicaladvisor.backend;

import java.util.ArrayList;

public interface RetrievingRepository<T> {

    interface RetrievingRepositoryCallback<T> {

        void onRetrievingDataSuccessfully(ArrayList<T> list);

        void onRetrievingDataFailed(String errmsg);
    }

    void retrieveAllData(RetrievingRepositoryCallback<T> callback);

    void retrieveDataById(String id, RetrievingRepositoryCallback<T> callback);
}
