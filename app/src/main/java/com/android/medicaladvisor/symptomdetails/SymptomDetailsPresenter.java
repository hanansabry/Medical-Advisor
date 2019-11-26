package com.android.medicaladvisor.symptomdetails;

import com.android.medicaladvisor.DiagnosisSharedPreferences;
import com.android.medicaladvisor.backend.RetrievingRepository;
import com.android.medicaladvisor.backend.SymptomsDetailsRepository;
import com.android.medicaladvisor.models.SymptomDetail;

public class SymptomDetailsPresenter {

    private final SymptomsDetailsRepository repository;
    private final DiagnosisSharedPreferences diagnosisSharedPreferences;

    public SymptomDetailsPresenter(SymptomsDetailsRepository repository, DiagnosisSharedPreferences diagnosisSharedPreferences) {
        this.repository = repository;
        this.diagnosisSharedPreferences = diagnosisSharedPreferences;
    }

    public void retrieveSymptomDetailsForSelectedInitialSymptom(String initialSymptomId,
                                                                RetrievingRepository.RetrievingRepositoryCallback<SymptomDetail> callback) {
        repository.retrieveDataById(initialSymptomId, callback);
    }

    public String getSelectedInitialSymptomId() {
        return diagnosisSharedPreferences.getSelectedInitialSymptomId();
    }

    public void setDiagnosisSymptomDetail(SymptomDetail symptomDetail) {
        diagnosisSharedPreferences.saveSelectedSymptomDetail(symptomDetail);
    }
}
