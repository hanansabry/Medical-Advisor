package com.android.medicaladvisor;

import android.app.Application;

import com.android.medicaladvisor.backend.MedicalCentersRepository;
import com.android.medicaladvisor.models.MedicalCenter;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //remove old shared preferences
//        getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE).edit().clear().apply();

//        RetrievingRepository<Person> repository = new PersonsRepository();
//        repository.retrieveDataById("111111", new RetrievingRepository.RetrievingRepositoryCallback<Person>() {
//            @Override
//            public void onRetrievingDataSuccessfully(ArrayList<Person> list) {
//                Log.d("Person", list.get(0).getName());
//            }
//
//            @Override
//            public void onRetrievingDataFailed(String errmsg) {
//                Log.d("Region", errmsg);
//            }
//        });

        MedicalCentersRepository repository = new MedicalCentersRepository();
        repository.retrieveMedicalCentersInRegionWithSpeciality(1, 6, new MedicalCentersRepository.MedicalCentersCallback() {
            @Override
            public void onRetrievingAvailableMedicalCenters(ArrayList<MedicalCenter> list) {

            }

            @Override
            public void onRetrievingMedicalCentersFailed(String errmsg) {

            }
        });
    }
}
