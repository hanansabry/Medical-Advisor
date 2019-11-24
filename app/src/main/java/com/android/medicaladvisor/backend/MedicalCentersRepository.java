package com.android.medicaladvisor.backend;

import com.android.medicaladvisor.models.MedicalCenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class MedicalCentersRepository {

    public interface MedicalCentersCallback {
        void onRetrievingAvailableMedicalCenters(ArrayList<MedicalCenter> list);

        void onRetrievingMedicalCentersFailed(String errmsg);
    }

    private static final String MEDICAL_CENTERS = "medicalCenters";

    private final DatabaseReference mDatabase;

    public MedicalCentersRepository() {
        mDatabase = FirebaseDatabase.getInstance().getReference(MEDICAL_CENTERS);
    }

    public void retrieveMedicalCentersInRegionWithSpeciality(final int regionId, final int specialityId, final MedicalCentersCallback callback) {
        mDatabase.orderByChild("regionId").equalTo(regionId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    ArrayList<MedicalCenter> medicalCenters = new ArrayList<>();
                    for (DataSnapshot centerSnapshot : dataSnapshot.getChildren()) {
                        DataSnapshot specialitiesSnapshot = centerSnapshot.child("specialities");
                        if (specialitiesSnapshot.exists()) {
                            for (DataSnapshot specialitySnapshot : specialitiesSnapshot.getChildren()) {
                                if (specialitySnapshot.getKey().equalsIgnoreCase(String.valueOf(specialityId))) {
                                    //get medical center data and add it to the list
                                    MedicalCenter medicalCenter = centerSnapshot.getValue(MedicalCenter.class);
                                    medicalCenters.add(medicalCenter);
                                    break;
                                }
                            }
                        } else {
                            callback.onRetrievingMedicalCentersFailed("No Available Medical centers in your region.");
                        }
                    }

                    if (medicalCenters.size() == 0) {
                        callback.onRetrievingMedicalCentersFailed("No Available Medical centers in your region.");
                    } else {
                        callback.onRetrievingAvailableMedicalCenters(medicalCenters);
                    }
                } else {
                    callback.onRetrievingMedicalCentersFailed("No Available Medical centers in your region.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onRetrievingMedicalCentersFailed(databaseError.getMessage());
            }
        });
    }
}
