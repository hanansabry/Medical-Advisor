package com.android.medicaladvisor.backend;

import com.android.medicaladvisor.models.Region;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class RegionsRepository implements RetrievingRepository<Region> {

    private static final String REGIONS = "regions";

    private final DatabaseReference mDatabase;

    public RegionsRepository() {
        mDatabase = FirebaseDatabase.getInstance().getReference(REGIONS);
    }

    @Override
    public void retrieveAllData(RetrievingRepositoryCallback<Region> callback) {

    }

    @Override
    public void retrieveDataById(final String regionId, final RetrievingRepositoryCallback<Region> callback) {
        mDatabase.child(regionId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Region region = dataSnapshot.getValue(Region.class);
                if (region != null) {
                    region.setId(dataSnapshot.getKey());
                    ArrayList<Region> regions = new ArrayList<>();
                    regions.add(region);
                    callback.onRetrievingDataSuccessfully(regions);
                } else {
                    callback.onRetrievingDataFailed("Can't find this region");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onRetrievingDataFailed(databaseError.getMessage());
            }
        });
    }
}
