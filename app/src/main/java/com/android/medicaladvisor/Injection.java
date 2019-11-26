package com.android.medicaladvisor;

import android.content.SharedPreferences;

import com.android.medicaladvisor.backend.CategoriesRepository;
import com.android.medicaladvisor.backend.DiagnosisRepository;
import com.android.medicaladvisor.backend.InitialSymptomsRepository;
import com.android.medicaladvisor.backend.MedicalCentersRepository;
import com.android.medicaladvisor.backend.PersonsRepository;
import com.android.medicaladvisor.backend.RegionsRepository;
import com.android.medicaladvisor.backend.SymptomsDetailsRepository;

public class Injection {
    public static CategoriesRepository provideCategoriesRepository() {
        return new CategoriesRepository();
    }

    public static DiagnosisSharedPreferences provideDiagnosisSharedPref(SharedPreferences sharedPreferences) {
        return new DiagnosisSharedPreferences(sharedPreferences);
    }

    public static InitialSymptomsRepository provideInitialSymptomsRepository() {
        return new InitialSymptomsRepository();
    }

    public static SymptomsDetailsRepository provideSymptomDetailsRepository() {
        return new SymptomsDetailsRepository();
    }

    public static DiagnosisRepository provideDiagnosisRepository() {
        return new DiagnosisRepository();
    }

    public static PersonsRepository providePersonsRepository() {
        return new PersonsRepository();
    }

    public static MedicalCentersRepository provideMedicalCentersRepository() {
        return new MedicalCentersRepository();
    }

    public static RegionsRepository provideRegionsRepository() {
        return new RegionsRepository();
    }
}
