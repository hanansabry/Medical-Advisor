package com.android.medicaladvisor.initialsymptoms;

import com.android.medicaladvisor.DiagnosisSharedPreferences;
import com.android.medicaladvisor.backend.InitialSymptomsRepository;
import com.android.medicaladvisor.backend.RetrievingRepository;
import com.android.medicaladvisor.models.InitialSymptom;

public class InitialSymptomPresenter {

    private final InitialSymptomsRepository repository;
    private final DiagnosisSharedPreferences diagnosisSharedPreferences;

    public InitialSymptomPresenter(InitialSymptomsRepository repository, DiagnosisSharedPreferences diagnosisSharedPreferences) {
        this.repository = repository;
        this.diagnosisSharedPreferences = diagnosisSharedPreferences;
    }

    public void getInitialSymptomsForSelectedCategory(
            String categoryId,
            RetrievingRepository.RetrievingRepositoryCallback<InitialSymptom> callback) {

        repository.retrieveDataById(categoryId, callback);
    }

    public String getSelectedCategoryId() {
        return diagnosisSharedPreferences.getSelectedCategoryId();
    }

    public void setDiagnosisInitialSymptom(InitialSymptom symptom) {
        diagnosisSharedPreferences.saveSelectedInitialSymptom(symptom);
    }
}
