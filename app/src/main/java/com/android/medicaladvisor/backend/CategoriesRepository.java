package com.android.medicaladvisor.backend;

import com.android.medicaladvisor.models.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class CategoriesRepository implements RetrievingRepository<Category> {

    private static final String CATEGORIES = "categories";

    private final DatabaseReference mDatabase;

    public CategoriesRepository() {
        mDatabase = FirebaseDatabase.getInstance().getReference(CATEGORIES);
    }

    @Override
    public void retrieveAllData(final RetrievingRepositoryCallback<Category> callback) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Category> categories = new ArrayList<>();
                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) {
                    Category category = new Category();
                    category.setId(categorySnapshot.getKey());
                    category.setName(categorySnapshot.child("name").getValue(String.class));

                    if (categorySnapshot.child("initialSymptoms").exists()) {
                        ArrayList<String> symptomsIds = new ArrayList<>();
                        for (DataSnapshot symptomsIdSnapshot : categorySnapshot.child("initialSymptoms").getChildren()) {
                            symptomsIds.add(symptomsIdSnapshot.getKey());
                        }
                        category.setInitialSymptoms(symptomsIds);
                    }
                    categories.add(category);
                }
                callback.onRetrievingDataSuccessfully(categories);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onRetrievingDataFailed(databaseError.getMessage());
            }
        });
    }

    @Override
    public void retrieveDataById(String id, RetrievingRepositoryCallback<Category> callback) {

    }
}
