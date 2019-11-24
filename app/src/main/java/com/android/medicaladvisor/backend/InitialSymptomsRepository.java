package com.android.medicaladvisor.backend;

import com.android.medicaladvisor.models.InitialSymptom;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class InitialSymptomsRepository implements RetrievingRepository<InitialSymptom> {

    private static final String INITIAL_SYMPTOMS = "initialSymptoms";

    private final DatabaseReference mDatabase;

    public InitialSymptomsRepository() {
        mDatabase = FirebaseDatabase.getInstance().getReference(INITIAL_SYMPTOMS);
    }

    @Override
    public void retrieveAllData(final RetrievingRepositoryCallback<InitialSymptom> callback) {

    }

    @Override
    public void retrieveDataById(final String categoryId, final RetrievingRepositoryCallback<InitialSymptom> callback) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<InitialSymptom> initialSymptoms = new ArrayList<>();
                for (DataSnapshot initialSymptomSnapshot : dataSnapshot.getChildren()) {
                    DataSnapshot categoryIdsSnapshot = initialSymptomSnapshot.child("categoryIds");
                    if (categoryIdsSnapshot.exists()) {
                        for (DataSnapshot categoryIdSnapshot : categoryIdsSnapshot.getChildren()) {
                            if (categoryId.equalsIgnoreCase(categoryIdSnapshot.getKey())) {
                                //get this symptom data and add this symptom to the array list
                                InitialSymptom initialSymptom = new InitialSymptom();
                                initialSymptom.setId(initialSymptomSnapshot.getKey());
                                initialSymptom.setName(initialSymptomSnapshot.child("name").getValue(String.class));
                                initialSymptom.setCategoryId(categoryId);

                                //get symptoms details list
                                DataSnapshot symptomDetailsIdsSnapshot = initialSymptomSnapshot.child("symptomDetails");
                                if (symptomDetailsIdsSnapshot.exists()) {
                                    ArrayList<String> symptomDetailsIds = new ArrayList<>();
                                    for (DataSnapshot symptomDetailIdSnapshot : categoryIdsSnapshot.getChildren()) {
                                        symptomDetailsIds.add(symptomDetailIdSnapshot.getKey());
                                    }
                                }

                                initialSymptoms.add(initialSymptom);
                            }
                        }
                    }
                }
                callback.onRetrievingDataSuccessfully(initialSymptoms);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onRetrievingDataFailed(databaseError.getMessage());
            }
        });
    }
}
