package com.android.medicaladvisor.backend;

import com.android.medicaladvisor.models.SymptomDetail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class SymptomsDetailsRepository implements RetrievingRepository<SymptomDetail> {

    private static final String SYMPTOM_DETAILS = "symptomDetails";

    private final DatabaseReference mDatabase;

    public SymptomsDetailsRepository() {
        mDatabase = FirebaseDatabase.getInstance().getReference(SYMPTOM_DETAILS);
    }

    @Override
    public void retrieveAllData(RetrievingRepositoryCallback<SymptomDetail> callback) {

    }

    @Override
    public void retrieveDataById(final String initialSymptomId, final RetrievingRepositoryCallback<SymptomDetail> callback) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<SymptomDetail> symptomDetailsList = new ArrayList<>();
                for (DataSnapshot symptomDetailSnapshot : dataSnapshot.getChildren()) {
                    DataSnapshot initialSymptomIdsSnapshot = symptomDetailSnapshot.child("initialSymptomIds");
                    if (initialSymptomIdsSnapshot.exists()) {
                        for (DataSnapshot initialSymptomIdSnapshot : initialSymptomIdsSnapshot.getChildren()) {
                            if (initialSymptomId.equalsIgnoreCase(initialSymptomIdSnapshot.getKey())) {
                                //get symptom detail data and add it to the list
                                SymptomDetail symptomDetail = new SymptomDetail();
                                symptomDetail.setId(symptomDetailSnapshot.getKey());
                                symptomDetail.setName(symptomDetailSnapshot.child("name").getValue(String.class));
                                symptomDetail.setInitialSymptomIds(initialSymptomId);

                                symptomDetailsList.add(symptomDetail);
                            }
                        }
                    }
                }
                if (symptomDetailsList.size() == 0) {
                    callback.onRetrievingDataFailed("There is no Symptom details for this Initial Symptom");
                } else {
                    callback.onRetrievingDataSuccessfully(symptomDetailsList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onRetrievingDataFailed(databaseError.getMessage());
            }
        });
    }
}
