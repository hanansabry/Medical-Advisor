package com.android.medicaladvisor.backend;

import com.android.medicaladvisor.models.Diagnosis;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

public class DiagnosisRepository {

    public interface DiagnosisCallback {
        void onDiagnosisDetected(Diagnosis diagnosis);

        void onDiagnosisDetectedFailed(String errmsg);
    }

    private static final String DIAGNOSISES = "diagnosises";

    private final DatabaseReference mDatabase;

    public DiagnosisRepository() {
        mDatabase = FirebaseDatabase.getInstance().getReference(DIAGNOSISES);
    }

    public void detectDiagnosis(
            final int categoryId,
            final int initialSymptomId,
            final int symptomDetailsId,
            final DiagnosisCallback callback) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean detected = false;
                Diagnosis diagnosis = null;
                for (DataSnapshot diagnosisSnapshot : dataSnapshot.getChildren()) {
                    diagnosis = diagnosisSnapshot.getValue(Diagnosis.class);
                    diagnosis.setId(diagnosisSnapshot.getKey());
                    if (diagnosis.getCategoryId() == categoryId
                            && diagnosis.getInitialSymptomId() == initialSymptomId
                            && diagnosis.getSymptomDetailsId() == symptomDetailsId) {
                        detected = true;
                        break;
                    }
                }

                if (detected) {
                    callback.onDiagnosisDetected(diagnosis);
                } else {
                    callback.onDiagnosisDetectedFailed("Can't detect diagnosis for these symptoms");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onDiagnosisDetectedFailed(databaseError.getMessage());
            }
        });
    }
}
