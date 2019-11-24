package com.android.medicaladvisor.backend;

import com.android.medicaladvisor.models.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class PersonsRepository implements RetrievingRepository<Person> {

    private static final String PERSONS = "persons";

    private final DatabaseReference mDatabase;

    public PersonsRepository() {
        mDatabase = FirebaseDatabase.getInstance().getReference(PERSONS);
    }

    @Override
    public void retrieveAllData(RetrievingRepositoryCallback<Person> callback) {

    }

    @Override
    public void retrieveDataById(String civilId, final RetrievingRepositoryCallback<Person> callback) {
        mDatabase.orderByChild("civilId").equalTo(Integer.parseInt(civilId)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Person person = snapshot.getValue(Person.class);
                        ArrayList<Person> persons = new ArrayList<>();
                        persons.add(person);
                        callback.onRetrievingDataSuccessfully(persons);
                    }
                } else {
                    callback.onRetrievingDataFailed("Can't find this Civil Id");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onRetrievingDataFailed(databaseError.getMessage());
            }
        });
    }
}
