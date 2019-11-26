package com.android.medicaladvisor.initialdiagnosis;

import com.android.medicaladvisor.DiagnosisSharedPreferences;
import com.android.medicaladvisor.backend.DiagnosisRepository;
import com.android.medicaladvisor.models.Diagnosis;

public class InitialDiagnosisPresenter {

    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisSharedPreferences diagnosisSharedPreferences;

    public InitialDiagnosisPresenter(DiagnosisRepository diagnosisRepository, DiagnosisSharedPreferences diagnosisSharedPreferences) {
        this.diagnosisRepository = diagnosisRepository;
        this.diagnosisSharedPreferences = diagnosisSharedPreferences;
    }

    private int getSelectedCategoryId() {
        return Integer.parseInt(diagnosisSharedPreferences.getSelectedCategoryId());
    }

    private int getSelectedInitialSymptomId() {
        return Integer.parseInt(diagnosisSharedPreferences.getSelectedInitialSymptomId());
    }

    private int getSelectedSymptomDetailId() {
        return Integer.parseInt(diagnosisSharedPreferences.getSelectedSymptomDetailId());
    }

    public String getSelectedCategoryName() {
        return diagnosisSharedPreferences.getSelectedCategoryName();
    }

    public String getSelectedInitialSymptomName() {
        return diagnosisSharedPreferences.getSelectedInitialSymptomName();
    }

    public String getSelectedSymptomDetailName() {
        return diagnosisSharedPreferences.getSelectedSymptomDetailName();
    }

    public void detectDiagnosis(DiagnosisRepository.DiagnosisCallback callback) {
        diagnosisRepository.detectDiagnosis(
                getSelectedCategoryId(),
                getSelectedInitialSymptomId(),
                getSelectedSymptomDetailId(),
                callback
        );
    }

    public void saveDiagnosis(Diagnosis diagnosis) {
        diagnosisSharedPreferences.setDiagnosis(diagnosis);
    }

}
