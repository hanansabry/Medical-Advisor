package com.android.medicaladvisor;

import android.content.SharedPreferences;

import com.android.medicaladvisor.models.Category;
import com.android.medicaladvisor.models.Diagnosis;
import com.android.medicaladvisor.models.InitialSymptom;
import com.android.medicaladvisor.models.SymptomDetail;

public class DiagnosisSharedPreferences {

    private final SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public DiagnosisSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveSelectedCategory(Category category) {
        editor = sharedPreferences.edit();
        editor.putString("CATEGORY_ID", category.getId());
        editor.putString("CATEGORY_NAME", category.getName());
        editor.apply();
    }

    public String getSelectedCategoryId() {
        return sharedPreferences.getString("CATEGORY_ID", "0");
    }

    public String getSelectedCategoryName() {
        return sharedPreferences.getString("CATEGORY_NAME", null);
    }

    public void saveSelectedInitialSymptom(InitialSymptom symptom) {
        editor = sharedPreferences.edit();
        editor.putString("INITIAL_SYMPTOM_ID", symptom.getId());
        editor.putString("INITIAL_SYMPTOM_NAME", symptom.getName());
        editor.apply();
    }

    public String getSelectedInitialSymptomId() {
        return sharedPreferences.getString("INITIAL_SYMPTOM_ID", "0");
    }

    public String getSelectedInitialSymptomName() {
        return sharedPreferences.getString("INITIAL_SYMPTOM_NAME", null);
    }

    public void saveSelectedSymptomDetail(SymptomDetail detail) {
        editor = sharedPreferences.edit();
        editor.putString("SYMPTOM_DETAIL_ID", detail.getId());
        editor.putString("SYMPTOM_DETAIL_NAME", detail.getName());
        editor.apply();
    }

    public String getSelectedSymptomDetailId() {
        return sharedPreferences.getString("SYMPTOM_DETAIL_ID", "0");
    }

    public String getSelectedSymptomDetailName() {
        return sharedPreferences.getString("SYMPTOM_DETAIL_NAME", null);
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        editor = sharedPreferences.edit();
        editor.putString("DIAGNOSIS_NAME", diagnosis.getName());
        editor.putInt("SPECIALITY_ID", diagnosis.getSpecialityId());
        editor.apply();
    }

    public String getDiagnosisName() {
        return sharedPreferences.getString("DIAGNOSIS_NAME", null);
    }

    public int getSpecialityId() {
        return sharedPreferences.getInt("SPECIALITY_ID", 0);
    }

    public void setRegionName(String regionName) {
        editor = sharedPreferences.edit();
        editor.putString("REGION_NAME", regionName);
        editor.apply();
    }

    public String getRegionName() {
        return sharedPreferences.getString("REGION_NAME", null);
    }

    public void setSelectedMedicalCenter(String name) {
        editor = sharedPreferences.edit();
        editor.putString("MEDICAL_CENTER_NAME", name);
        editor.apply();
    }

    public String getSelectedMedicalCenterName() {
        return sharedPreferences.getString("MEDICAL_CENTER_NAME", null);
    }
}
